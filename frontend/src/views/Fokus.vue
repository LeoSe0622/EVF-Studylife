<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import axios from 'axios'

type Todo = {
  id: number
  text: string
  erledigt: boolean
}

const baseUrl = import.meta.env.VITE_APP_BACKEND_BASE_URL

// =====================================================
// To-Dos
// =====================================================
const todos = ref<Todo[]>([])
const neuesTodo = ref('')

// alle To-Dos vom Backend holen
function requestTodos(): void {
  axios
    .get<Todo[]>(`${baseUrl}/fokus/todos`)
    .then((response) => (todos.value = response.data))
    .catch((error) => console.log(error))
}

// neues To-Do speichern und danach die Liste neu laden
function addTodo(): void {
  if (neuesTodo.value.trim() === '') return
  axios
    .post(`${baseUrl}/fokus/todos`, { text: neuesTodo.value })
    .then(() => {
      neuesTodo.value = ''
      requestTodos()
    })
    .catch((error) => console.log(error))
}

// To-Do abhaken und danach die Liste neu laden
function abhaken(id: number): void {
  axios
    .put(`${baseUrl}/fokus/todos/${id}/abhaken`)
    .then(() => requestTodos())
    .catch((error) => console.log(error))
}

// To-Do löschen und danach die Liste neu laden
function todoLoeschen(id: number): void {
  axios
    .delete(`${baseUrl}/fokus/todos/${id}`)
    .then(() => requestTodos())
    .catch((error) => console.log(error))
}

// =====================================================
// Timer (fest auf 25 Minuten)
// =====================================================
const startSekunden = 25 * 60
const seconds = ref(startSekunden)
const running = ref(false)
let interval: number | undefined

// macht aus z. B. 1500 Sekunden die Anzeige "25:00"
const display = computed(() => {
  const m = String(Math.floor(seconds.value / 60)).padStart(2, '0')
  const s = String(seconds.value % 60).padStart(2, '0')
  return `${m}:${s}`
})

// Start / Pause
function toggle(): void {
  if (running.value) {
    clearInterval(interval)
    running.value = false
  } else {
    running.value = true
    // jede Sekunde eins runterzählen, bei 0 anhalten
    interval = setInterval(() => {
      if (seconds.value > 0) {
        seconds.value--
      } else {
        clearInterval(interval)
        running.value = false
      }
    }, 1000)
  }
}

// Timer anhalten und wieder auf 25:00 setzen
function reset(): void {
  clearInterval(interval)
  running.value = false
  seconds.value = startSekunden
}

// beim Öffnen der Seite die To-Dos laden
onMounted(() => requestTodos())

// beim Verlassen der Seite den Timer stoppen
onUnmounted(() => clearInterval(interval))
</script>

<template>
  <div class="page">
    <!-- linke Seite: To-Do-Liste -->
    <div class="todos">
      <h1>To-Dos</h1>

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

    <!-- rechte Seite: Timer -->
    <div class="timer-area">
      <div class="timer-circle">
        <span class="timer-label">Timer</span>
        <span class="timer-time">{{ display }}</span>
      </div>

      <div class="buttons">
        <button class="start-btn" @click="toggle()">
          {{ running ? 'Pause' : 'Start' }}
        </button>
        <button class="start-btn" @click="reset()">Reset</button>
      </div>
    </div>
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
  display: flex;
  gap: 40px;
}

/* ----- To-Dos ----- */
.todos {
  flex: 1;
}
h1 {
  font-size: 3em;
  margin: 0 0 30px 0;
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
.check.checked {
  background-color: #555555;
}
.todo-text {
  flex: 1;
}
/* erledigte Einträge durchstreichen */
.todo-text.done {
  text-decoration: line-through;
  opacity: 0.5;
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

/* ----- Timer ----- */
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
.buttons {
  display: flex;
  gap: 10px;
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
</style>
