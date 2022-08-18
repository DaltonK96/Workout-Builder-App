<template>
  <div class="home">
    <h1 id="home-link">Home</h1>
    <nav>
    <router-link v-bind:to="{ name: 'AddExercise' }">Add Exercise</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'ListExercise' }">View Exercises</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'GenerateRandom' }">Generate Random</router-link>&nbsp;|&nbsp;
    </nav>
  
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
