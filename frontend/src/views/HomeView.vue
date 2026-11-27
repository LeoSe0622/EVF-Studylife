<script setup lang="ts">
import Einkaufsliste from '@/components/Einkaufsliste.vue'
import {RouterLink} from "vue-router";
const schritte = [
  { titel: 'Budget festlegen', text: 'Zu Monatsbeginn trägst du ein, wie viel Geld du einplanst.' },
  { titel: 'Einkaufsliste schreiben', text: 'TODO' },
]
</script>

<template>
  <main>
    <!-- Einstieg: Was ist das hier? -->
    <section class="hero">
      <h1>Willkommen bei EVF-Studylife</h1>
      <p>EVF hilft dir alles zu Organisieren, von Einkauf für Vorrat bis zu deinen TODOS</p>
      <RouterLink to="/Dashboard">Zum Dashboard</RouterLink>
    </section>

    <!-- Probleme: Betrifft mich das? -->
    <section class="probleme">
      <h2>Kommt dir das bekannt vor?</h2>
      <ul>
        <li>Reicht dein Geld nicht bis Monatsende?</li>
        <li>Fällt es dir schwer, dein Budget einzuhalten?</li>
        <li>Keine Ahnung, was in deinem Kühlschrank ist?</li>
      </ul>
    </section>

    <!-- Ablauf: Wie benutze ich das? -->
    <section class="ablauf">
      <h2>So funktioniert's</h2>
      <ol>
        <li v-for="schritt in schritte" :key="schritt.titel">
          <h3>{{ schritt.titel }}</h3>
          <p>{{ schritt.text }}</p>
        </li>
      </ol>
    </section>

    <!-- Bereiche: Wo geht's weiter? -->
    <section class="bereiche">
      <h2>Deine Bereiche</h2>
      <RouterLink to="/GList">Einkaufsliste</RouterLink>
      <RouterLink to="/Stocklist">Vorratsliste</RouterLink>
      <RouterLink to="/Fokus">Fokusmode</RouterLink>
    </section>
  </main>
</template>
