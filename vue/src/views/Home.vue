<template>
  <div class="home">
    <h1 id="home-link">Home</h1>
    <nav>
    <router-link v-bind:to="{ name: 'AddExercise' }">Add Exercise</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'ListExercise' }">View Exercises</router-link>&nbsp;|&nbsp;
   
    </nav>
  <img src="../imgChristopher\D6730372-6145-4A4D-8C4B-11EBCFA30776.jpg" id="muscles">
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

