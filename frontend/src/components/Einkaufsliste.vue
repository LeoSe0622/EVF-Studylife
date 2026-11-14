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

</script>

<template>

  <h2>Einkaufsliste</h2>
  <input v-model="neuerArtikel" placeholder="Artikelname" />
  <input v-model.number="menge" type="number" />
  <button @click="addArtikel()">Hinzufügen</button>
  <button @click="requestArtikel()">Liste Laden laden</button>

  <ul>
    <li v-for="e in items" :key="e.id">
      {{ e.menge }}× {{ e.artikel.name }}
      <button @click="abhaken(e.id)">Gekauft</button>
    </li>
  </ul>
</template>
