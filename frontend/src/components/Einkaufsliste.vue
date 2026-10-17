<script setup lang="ts">
type Artikel = { name: string }
type User = { name: string; email: string; einkaufsliste: Artikel[] }

const user: User = {
  name: 'Demo-User',
  email: 'demo@example.com',
  einkaufsliste: [{ name: 'Milch' }, { name: 'Eier' }, { name: 'Spaghetti' }, { name: 'Tomaten' }],
}
</script>

<template>
  <h2>Einkaufsliste von {{ user.name }}</h2>
  <ul>
    <li v-for="(artikel, index) in user.einkaufsliste" :key="index">
      {{ artikel.name }}
    </li>
  </ul>
  <p v-if="user.einkaufsliste.length === 0">Die Liste ist leer.</p>
</template>
