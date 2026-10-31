<script setup lang="ts">
import HelloWorld from '@/components/HelloWorld.vue'
</script>
<template>
  <h1>Dashboard</h1>
  <div class="budget">
    <div>Budget</div>
    <div class="bugetBox">0.00</div>
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

</style>
