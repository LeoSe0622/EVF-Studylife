<script setup lang="ts">
</script>

<template>
  <div class="page">
    <h1>Vorratsliste</h1>

    <table class="stock-table">
      <thead>
      <tr>
        <th></th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
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
}
h1 {
  font-size: 3em;
  margin: 0 0 30px 0;
}
.stock-table {
  width: 100%;
  border-collapse: collapse;
}
.stock-table th {
  border-bottom: 2px solid #555555;
  padding: 10px;
  text-align: left;
}
</style>
