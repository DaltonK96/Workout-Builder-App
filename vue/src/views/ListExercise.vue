<template>
  <div>
     
        
      
      <table>
          <thead>
              <tr>
                
              </tr>
          </thead>
          <tbody>
              <tr v-for="workout in $store.state.uniqueCards" v-bind:key="workout.workoutId">
                  <td>{{workout.workoutId}}</td>
                  <td>{{workout.workoutName}}</td>
                  <td>{{workout.workoutDesc}}</td>
                  <td>
                      <router-link v-bind:to="{name: 'Edit', params: {workoutId: workout.workoutId}}">Edit</router-link>
                      &nbsp; &nbsp;
                      <a href="#" v-on:click="deleteExercise(workout.workoutId)" >Delete</a>
                  </td>
              </tr>
          </tbody>
      </table>
  </div>
</template>

<script>
import WorkoutService from "@/services/WorkoutService.js";

export default {
    name: 'list-workouts',
    data() {
        return {
            cards : [],
            errorMsg: ""
        }
    },
    created() {
        this.workouts();
    }, 
    methods: {
        workouts() {
            WorkoutService.getUniqueWorkouts().then(
                response => {
                     [],   
                    this.workouts = response.data;
                    this.$store.commit("SET_UNIQUE_LIST_EXERCISE", this.workouts);
                }
            )
        },
        deleteWorkout(workoutId) {
            WorkoutService.deleteCard(workoutId).then(
                () => {
                    this.workouts();
                }
            )
            .catch(
                error => {
                    if(error.response) {
                        this.errorMsg = error.response.statusText;
                    } else if (error.request) {
                        this.errorMsg = "We couldn't find the server";
                    } else {
                        this.errorMsg = "Error - we couldn't create the request";
                    }
                }
            );
        }

    }
}
</script>

<style>

</style>
        

