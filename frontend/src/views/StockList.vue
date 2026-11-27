<script setup lang="ts">

import { ref } from 'vue'
import axios from 'axios'

type Eintrag = {
  id: number
  menge: number
  artikel: { name: string }
}

const items = ref<Eintrag[]>([])
const baseUrl = import.meta.env.VITE_APP_BACKEND_BASE_URL


function requestArtikel(): void {
  axios
    .get<Eintrag[]>(`${baseUrl}/stockliste`)
    .then((response) => (items.value = response.data))
    .catch((error) => console.log(error))
}

// Eingabe und Zustand für den Rezeptvorschlag
const wunsch = ref('')      // optionaler Wunsch aus dem Textfeld
const rezept = ref('')      // Antwort von Gemini
const fehler = ref('')      // Fehlermeldung, falls etwas schiefgeht
const laedt = ref(false)    // true, solange wir auf die Antwort warten

function rezeptVorschlagen(): void {
  laedt.value = true
  rezept.value = ''
  fehler.value = ''

  axios
    .post<{ text: string }>(`${baseUrl}/api/rezeptvorschlag`, { text: wunsch.value })
    .then((response) => (rezept.value = response.data.text))
    .catch((error) => {
      console.log(error)
      // Bei 503 schickt das Backend eine verständliche Meldung im Feld "text"
      fehler.value = error.response?.data?.text ?? 'Das Backend ist nicht erreichbar.'
    })
    .finally(() => (laedt.value = false))
}
</script>

<template>
  <div class="page">
    <h1>Vorratsliste</h1>

    <button @click="requestArtikel()">Liste laden</button>

    <table class="stock-table">
      <thead>
      <tr>
        <th>Artikel</th><th>Menge</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="e in items" :key="e.id">
        <td>{{ e.artikel.name }}</td>
        <td>{{ e.menge }}</td>
      </tr>
      </tbody>
    </table>

  <div class="rezept-bereich">
  <h2>Rezeptvorschlag</h2>

  <input
    v-model="wunsch"
    type="text"
    maxlength="200"
    placeholder="Wunsch (optional), z. B. vegetarisch, unter 30 Minuten"
  />

  <button @click="rezeptVorschlagen()" :disabled="laedt">
    {{ laedt ? 'Rezept wird erstellt…' : 'Rezept vorschlagen' }}
  </button>

  <p v-if="fehler" class="fehler">{{ fehler }}</p>
  <div v-if="rezept" class="rezept-text">{{ rezept }}</div>
</div>
  </div>
</template>

<style scoped>
.page {
  background-color: #121212;
  color: #ffffff;
  font-family: Arial, sans-serif;
  min-height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}
h1 {
  font-size: 3em;
  margin: 0 0 30px 0;
}
.stock-table {
  width: 100%;
  border-collapse: collapse;
}
.stock-table th {
  border-bottom: 2px solid #555555;
  padding: 10px;
  text-align: left;
}

.rezept-bereich {
  margin-top: 40px;
}
.rezept-bereich input {
  width: 100%;
  max-width: 500px;
  padding: 8px;
  margin: 10px 10px 10px 0;
  box-sizing: border-box;
}
.fehler {
  color: #ff6b6b;
}
.rezept-text {
  margin-top: 20px;
  padding: 15px;
  border: 1px solid #555555;
  border-radius: 6px;
  white-space: pre-wrap; /* Zeilenumbrüche aus der Gemini-Antwort behalten */
  max-width: 700px;
}
</style>
