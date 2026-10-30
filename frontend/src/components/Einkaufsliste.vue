<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'

type Artikel = {
  name: string
}

const items = ref<Artikel[]>([])
const baseUrl = import.meta.env.VITE_APP_BACKEND_BASE_URL

const neuerArtikel = ref('')


function requestArtikel(): void {
  axios
    .get<Artikel[]>(`${baseUrl}/`)
    .then((response) => (items.value = response.data))
    .catch((error) => console.log(error))
}

function addArtikel(): void{
  axios
    .post<Artikel>(`${baseUrl}/ArtikelE`,  {name : neuerArtikel.value})
    .then((respone) => {items.value.push(respone.data)
      neuerArtikel.value= ''})
    .catch((error) => console.log(error))
}

</script>

<template>
  <h2>Einkaufsliste</h2>
  <input v-model="neuerArtikel" placeholder="Artikelname" />
  <button @click="addArtikel()">Hinzufügen</button>
  <button @click="requestArtikel()">Artikel laden</button>
  <ul>
    <li v-for="(artikel, index) in items" :key="index">
      {{ artikel.name }}
    </li>
  </ul>
</template>
