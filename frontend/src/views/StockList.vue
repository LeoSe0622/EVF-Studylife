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
</style>
