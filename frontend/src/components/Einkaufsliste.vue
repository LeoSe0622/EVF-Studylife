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

const neuerArtikel = ref('')
const menge = ref(1)

//ref damit sich ändern kann, Array zum speichern der abgehackten ids
const gekauft = ref<number[]>([])   // IDs der abgehakten Einträge

const zeigeKosten = ref(false)   // Kosten-Eingabe sichtbar?
const kosten = ref(0)            // eingegebener Betrag

function requestArtikel(): void {
  axios
    .get<Eintrag[]>(`${baseUrl}/einkaufsliste`)
    .then((response) => (items.value = response.data))
    .catch((error) => console.log(error))
}

function addArtikel(): void{
  axios
    .post(`${baseUrl}/einkaufsliste`,  {name : neuerArtikel.value,menge: menge.value})
    .then((respone) => {items.value.push(respone.data)
      neuerArtikel.value= ''})
    .catch((error) => console.log(error))
}
function abhaken(id: number): void {
  axios
    .put(`${baseUrl}/eintrag/${id}/abhaken`)
    .then(() => requestArtikel())
    .catch((error) => console.log(error))
}

function einkaufAbschließen(): void{
  axios
    .put(`${baseUrl}/einkaufsliste/abschliessen`, gekauft.value)
    .then(() => {
      // gekauft const leeren
      gekauft.value = []
      //aktikel neu ausgeben da ja jetzt abgeschlossen
      requestArtikel()
      zeigeKosten.value = true   // jetzt nach den Kosten fragen
    })
    .catch((error) => console.log(error))
}

function kostenAbziehen(): void {
  axios
    .put(`${baseUrl}/api/budget/ausgabe`, { amount: kosten.value })
    .then(() => {
      kosten.value = 0
      zeigeKosten.value = false   // Eingabe wieder ausblenden
    })
    .catch((error) => console.log(error))
}

</script>

<template>

  <h2>Einkaufsliste</h2>
  <input v-model="neuerArtikel" placeholder="Artikelname" />
  <input v-model.number="menge" type="number" />
  <button @click="addArtikel()">Hinzufügen</button>
  <button @click="requestArtikel()">Liste Laden laden</button>

  <ul>
    <li v-for="e in items" :key="e.id">
      <input type="checkbox" :value="e.id" v-model="gekauft" /> <!-- in die const binden-->
      <span>{{ e.menge }}× {{ e.artikel.name }}</span> <!-- span um zu sagen was durchgestrichen werden soll-->

    </li>
  </ul>
  <!-- um einkauf abzuschließen-->
  <button @click="einkaufAbschließen()">Einkauf abschließen</button>


  <!-- erscheint erst nach dem Abschließen -->
  <div v-if="zeigeKosten">
    <p>Was hat der Einkauf gekostet?</p>
    <input v-model.number="kosten" type="number" />
    <button @click="kostenAbziehen()">Abziehen</button>
  </div>


</template>

<style>
/* für alle input elemente die in span stehen*/
input:checked + span {
  text-decoration: line-through;
  opacity: 0.5;
}

.span{
  font-size: 400%;
}

</style>
