<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <header>
    <div class="wrapper">
      <HelloWorld msg="EVF-Studylife" />
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/Dashboard">Dashboard</RouterLink>
        <RouterLink to="/Stocklist">Vorratsliste</RouterLink>
        <RouterLink to="/Fokus">Fokusmode</RouterLink>
      </nav>
    </div>

    <img alt="Vue logo" class="logo" src="@/assets/MainLogo.svg" width="75" height="75" />
  </header>

  <RouterView />
</template>

<style scoped>
header {
  line-height: 1.5;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}
.titelleiste {
  display: flex;
  align-items: center;
  background: hsla(160, 100%, 37%, 1);
  color: black;
  padding: 1rem;
}

nav,
.inhalt {
  padding: 1rem;
}

.logo {
  display: block;
}
.navigation {
  display: flex;
  gap: 1.5rem;
  padding: 0.75rem 1.5rem;
  border-bottom: 1px solid #ddd;
}

</style>

