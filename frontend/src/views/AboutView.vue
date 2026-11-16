<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'

type BudgetResponse = {
  amount: number
}

const baseUrl = import.meta.env.VITE_APP_BACKEND_BASE_URL

const budget = ref(0)        // aktuell gespeichertes Budget
const input = ref('')        // Text im Eingabefeld
const error = ref('')        // Fehlermeldung für den User

// Budget vom Backend holen
function requestBudget(): void {
  axios
    .get<BudgetResponse>(`${baseUrl}/api/budget`)
    .then((response) => (budget.value = response.data.amount))
    .catch((e) => {
      error.value = 'Budget konnte nicht geladen werden. Läuft das Backend?'
      console.log(e)
    })
}

// Neues Budget an das Backend schicken
function saveBudget(): void {
  error.value = ''

  // Komma als Dezimaltrenner erlauben ("12,50" -> 12.5)
  const amount = Number(input.value.replace(',', '.'))

  if (input.value.trim() === '' || !Number.isFinite(amount) || amount < 0) {
    error.value = 'Bitte einen gültigen Betrag eingeben.'
    return
  }

  axios
    .put<BudgetResponse>(`${baseUrl}/api/budget`, { amount })
    .then((response) => {
      budget.value = response.data.amount
      input.value = ''
    })
    .catch((e) => {
      error.value = 'Budget konnte nicht gespeichert werden.'
      console.log(e)
    })
}

// Beim Öffnen der Seite automatisch laden
onMounted(requestBudget)
</script>

<template>
  <h1>Dashboard</h1>
  <div class="budget">
    <div>Budget</div>
    <div class="bugetBox">{{ budget.toFixed(2) }}</div>
  </div>

  <div class="budgetForm">
    <input
      v-model="input"
      type="text"
      inputmode="decimal"
      placeholder="Neues Budget, z. B. 250.50"
      @keyup.enter="saveBudget"
    />
    <button @click="saveBudget">Speichern</button>
    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<style>

.page {
  background-color: #121212;
  color: #ffffff;
  font-family: Arial, sans-serif;
  min-height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}
h1 {
  font-size: 3em;
  margin: 0;
}
.budget {
  text-align: right;
  margin-top: 40px;
  font-size: 40px;
}
.bugetBox{
  display: inline-block;
  border: 1px solid;
  border-radius: 10px;
  padding: 4px 10px;
  margin-top: 4px;
}
.budgetForm {
  text-align: right;
  margin-top: 16px;
}
.budgetForm input {
  padding: 6px 10px;
  border-radius: 8px;
  border: 1px solid;
  margin-right: 8px;
}
.budgetForm button {
  padding: 6px 14px;
  border-radius: 8px;
  cursor: pointer;
}
.error {
  color: #ff6b6b;
  margin-top: 8px;
}

</style>
