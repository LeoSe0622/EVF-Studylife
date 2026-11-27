<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import axios from 'axios'

type Todo = {
  id: number
  text: string
  erledigt: boolean
}

type Timer = {
  id: number
  name: string
  minuten: number
}

const baseUrl = import.meta.env.VITE_APP_BACKEND_BASE_URL

// ---------- To-Dos ----------
const todos = ref<Todo[]>([])
const neuesTodo = ref('')

// ---------- Timer-Liste ----------
const timerListe = ref<Timer[]>([])
const neuerTimerName = ref('')
const neueMinuten = ref(25)

// ---------- Countdown ----------
const startSeconds = ref(25 * 60)   // ref, damit man den Timer wechseln kann
const seconds = ref(startSeconds.value)
const running = ref(false)
let interval: number | undefined

const display = computed(() => {
  const m = String(Math.floor(seconds.value / 60)).padStart(2, '0')
  const s = String(seconds.value % 60).padStart(2, '0')
  return `${m}:${s}`
})

function toggle() {
  if (running.value) {
    clearInterval(interval)
    running.value = false
    return
  }
  if (seconds.value === 0) seconds.value = startSeconds.value
  running.value = true
  interval = setInterval(() => {
    if (seconds.value > 0) {
      seconds.value--
    } else {
      clearInterval(interval)
      running.value = false
    }
  }, 1000)
}

// gespeicherten Timer auswählen -> Countdown neu setzen
function timerWaehlen(timer: Timer): void {
  if (running.value) return   // während er läuft nicht wechseln
  startSeconds.value = timer.minuten * 60
  seconds.value = startSeconds.value
}

// ---------- To-Do Requests ----------
function requestTodos(): void {
  axios
    .get<Todo[]>(`${baseUrl}/fokus/todos`)
    .then((response) => (todos.value = response.data))
    .catch((error) => console.log(error))
}

function addTodo(): void {
  if (neuesTodo.value.trim() === '') return
  axios
    .post(`${baseUrl}/fokus/todos`, { text: neuesTodo.value })
    .then((response) => {
      todos.value.push(response.data)
      neuesTodo.value = ''
    })
    .catch((error) => console.log(error))
}

function abhaken(id: number): void {
  axios
    .put(`${baseUrl}/fokus/todos/${id}/abhaken`)
    .then(() => requestTodos())
    .catch((error) => console.log(error))
}

function todoLoeschen(id: number): void {
  axios
    .delete(`${baseUrl}/fokus/todos/${id}`)
    .then(() => requestTodos())
    .catch((error) => console.log(error))
}

// ---------- Timer Requests ----------
function requestTimer(): void {
  axios
    .get<Timer[]>(`${baseUrl}/fokus/timer`)
    .then((response) => (timerListe.value = response.data))
    .catch((error) => console.log(error))
}

function addTimer(): void {
  if (neuerTimerName.value.trim() === '' || neueMinuten.value <= 0) return
  axios
    .post(`${baseUrl}/fokus/timer`, { name: neuerTimerName.value, minuten: neueMinuten.value })
    .then((response) => {
      timerListe.value.push(response.data)
      neuerTimerName.value = ''
      neueMinuten.value = 25
    })
    .catch((error) => console.log(error))
}

function timerLoeschen(id: number): void {
  axios
    .delete(`${baseUrl}/fokus/timer/${id}`)
    .then(() => requestTimer())
    .catch((error) => console.log(error))
}

// beim Öffnen der Seite direkt alles laden
onMounted(() => {
  requestTodos()
  requestTimer()
})

onUnmounted(() => clearInterval(interval))
</script>

<template>
  <div class="page">
    <div class="todos">
      <h1>To-Dos</h1>

      <!-- neuen einzelnen Eintrag anlegen -->
      <div class="eingabe">
        <input v-model="neuesTodo" placeholder="Neue Aufgabe" @keyup.enter="addTodo()" />
        <button class="start-btn" @click="addTodo()">Hinzufügen</button>
      </div>

      <ul class="todo-list">
        <li v-for="t in todos" :key="t.id" class="todo-item">
          <!-- Kreis zum Abhaken, gefüllt wenn erledigt -->
          <span class="check" :class="{ checked: t.erledigt }" @click="abhaken(t.id)"></span>
          <span class="todo-text" :class="{ done: t.erledigt }">{{ t.text }}</span>
          <button class="loeschen" @click="todoLoeschen(t.id)">✕</button>
        </li>
      </ul>
    </div>

    <div class="timer-area">
      <div class="timer-circle">
        <span class="timer-label">Timer</span>
        <span class="timer-time">{{ display }}</span>
      </div>
      <button class="start-btn" @click="toggle">
        {{ running ? 'Pause' : 'Start' }}
      </button>

      <!-- gespeicherte Timer zum Auswählen -->
      <div class="timer-liste">
        <div v-for="tm in timerListe" :key="tm.id" class="timer-chip">
          <span @click="timerWaehlen(tm)">{{ tm.name }} · {{ tm.minuten }} min</span>
          <button class="loeschen" @click="timerLoeschen(tm.id)">✕</button>
        </div>
      </div>

      <!-- neuen Timer festlegen -->
      <div class="eingabe">
        <input v-model="neuerTimerName" placeholder="Name" />
        <input v-model.number="neueMinuten" type="number" min="1" class="minuten" />
        <button class="start-btn" @click="addTimer()">Speichern</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* ----- bestehende Styles bleiben gleich ----- */
.page {
  background-color: #121212;
  color: #ffffff;
  font-family: Arial, sans-serif;
  min-height: 100vh;
  padding: 20px;
  box-sizing: border-box;
  display: flex;
  gap: 40px;
}
.todos {
  flex: 1;
}
h1 {
  font-size: 3em;
  margin: 0 0 30px 0;
}
.todo-list {
  list-style: none;
  padding: 0;
  margin: 0;
}
.todo-item {
  display: flex;
  align-items: center;
  gap: 14px;
  padding: 14px 0;
  border-bottom: 1px solid #2a2a2a;
}
.check {
  width: 18px;
  height: 18px;
  border: 2px solid #555555;
  border-radius: 50%;
  flex-shrink: 0;
  cursor: pointer;
}
.timer-area {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  margin-top: 80px;
}
.timer-circle {
  width: 220px;
  height: 220px;
  border: 4px solid #555555;
  border-radius: 50%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.timer-label {
  color: #aaaaaa;
  font-size: 1.1em;
}
.timer-time {
  font-size: 2.8em;
  font-weight: bold;
  margin-top: 6px;
}
.start-btn {
  background-color: #2a2a2a;
  color: #ffffff;
  border: 1px solid #555555;
  border-radius: 20px;
  padding: 10px 36px;
  font-size: 1em;
  cursor: pointer;
}
.start-btn:hover {
  background-color: #3a3a3a;
}

/* ----- neu ----- */
.check.checked {
  background-color: #555555;
}
.todo-text {
  flex: 1;
}
/* erledigte Einträge durchstreichen wie in der Einkaufsliste */
.todo-text.done {
  text-decoration: line-through;
  opacity: 0.5;
}
.eingabe {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}
.eingabe input {
  background-color: #1e1e1e;
  color: #ffffff;
  border: 1px solid #555555;
  border-radius: 20px;
  padding: 10px 16px;
  font-size: 1em;
}
.eingabe .minuten {
  width: 70px;
}
.loeschen {
  background: none;
  border: none;
  color: #555555;
  cursor: pointer;
  font-size: 1em;
}
.loeschen:hover {
  color: #ffffff;
}
.timer-liste {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
  max-width: 320px;
}
.timer-chip {
  display: flex;
  align-items: center;
  gap: 6px;
  background-color: #1e1e1e;
  border: 1px solid #555555;
  border-radius: 20px;
  padding: 6px 12px;
  cursor: pointer;
}
</style>
