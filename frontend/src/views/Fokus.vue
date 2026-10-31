<script setup lang="ts">
import { ref, computed, onUnmounted } from 'vue'

const startSeconds = 25 * 60
const seconds = ref(startSeconds)
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
  if (seconds.value === 0) seconds.value = startSeconds
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

onUnmounted(() => clearInterval(interval))
</script>

<template>
  <div class="page">
    <div class="todos">
      <h1>To-Dos</h1>
      <ul class="todo-list">
        <li v-for="n in 4" :key="n" class="todo-item">
          <span class="check"></span>
          <span class="placeholder"></span>
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
}
.placeholder {
  height: 10px;
  width: 60%;
  background-color: #1e1e1e;
  border-radius: 5px;
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
</style>
