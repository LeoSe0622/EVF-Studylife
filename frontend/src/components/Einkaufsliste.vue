<script setup lang="ts">
import { reactive } from 'vue'

type Artikel = {
  name: string
}

const items = reactive<Artikel[]>([])




function loadThings () {
  console.log('Klick angekommen')
  const endpoint = 'http://localhost:8080/data'
  const requestOptions = {
  method: 'GET',
  redirect: 'follow'
  }

  fetch(endpoint, requestOptions)
  .then(response => response.json())
  .then(result => result.forEach(thing => {
  items.push(thing)
    console.log(items);
  }))
  .catch(error => console.log('error',error))
}
</script>

<template>
  <h2>Einkaufsliste</h2>
  <button @click="loadThings()">Artikel laden</button>
  <ul>
    <li v-for="(artikel, index) in items" :key="index">
      {{ artikel.name }}
    </li>
  </ul>
</template>
