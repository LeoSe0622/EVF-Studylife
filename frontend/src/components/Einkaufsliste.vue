<script setup lang="ts">

const items = {string: name}

function loadThings () {
  console.log('Klick angekommen')
  const endpoint = 'http://localhost:8080/'
  const requestOptions = {
  method: 'GET',
  redirect: 'follow'
  }

  fetch(endpoint, requestOptions)
  .then(response => response.json())
  .then(result => result. forEach(thing => {
  this.items.push(thing)
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
