<script setup lang="ts">
import Einkaufsliste from '@/components/Einkaufsliste.vue'
import {RouterLink} from "vue-router";
</script>

<template>
  <main>
    <h1> Willkommen bei EVF-Studylife</h1>

    <p>Welches Problem löst unser Projekt?
      *Reicht dein geld nicht bis Monatsende?

      *Schwierigkeiten dein Buget einzuhalten?

      *Du hast keine Ahnung was in deinem Kühlschrank ist?

      genau bei diesen Problemen sorgt EVF-Studylife für abhilfe!
      Durch eine Einkaufsliste, mit einer enschließenden einpflege in deine
      Digitale Vorratskammer, verlierst du weder den überblick was du
      einkaufen musst noch was du in deinem Vorrat hast. Da du jeden Monat ein
      Buget festlegst, kannst du genau im Auge behalten wieviel Geld du noch hast.</p>
    <RouterLink to="/">Home</RouterLink>
    <RouterLink to="/Dashboard">Dashboard</RouterLink>
    <RouterLink to="/Stocklist">Vorratsliste</RouterLink>
    <RouterLink to="/Fokus">Fokusmode</RouterLink>
  </main>
</template>
