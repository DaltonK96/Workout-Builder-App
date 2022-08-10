<template>
  <div id="add-exercise">
      <form class="form-add-exercise" @submit.prevent="addExercise" >
  <h1 id="add-exercise-test" >Add Exercise</h1>

</template>

<script>
import WorkoutService  from "../services/WorkoutService";

export default {
    name: "addExercise",
    components: {},
    data(){
        return {
            workout: {
                workoutName: '',
                workoutDesc: '',
                expectedTarget: '',
            }

        }
    },
    method: {
        addExercise(){
            
        }

    }
}
</script>

