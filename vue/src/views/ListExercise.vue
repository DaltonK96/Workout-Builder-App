<template>
  <div>
    <h1> List of Exercises </h1>
      
      <table>

          <thead>
              <tr>
                
              </tr>
          </thead>
          <tbody>
              <tr v-for="(workout,index) in $store.state.uniqueWorkouts" v-bind:key="index">
                  <td>{{workout.workoutId}}</td>
                  <td>{{workout.workoutName}}</td>
                  <td>{{workout.workoutDesc}}</td>
                  <td>{{workout.expectedTarget}}</td>
                  <td>{{workout.weight}}</td>
                  <td>{{workout.repSet}}</td>
                  <td>{{workout.time}}</td>
                  
                  <td>
                       
                      <router-link v-bind:to="{name: 'EditExercise', params: {workoutId: workout.workoutId}}">Edit</router-link>
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
            workouts : [],
            errorMsg: ""
        }
    },
    created() {
        this.loadAllFullWorkouts();
    }, 
    methods: {
        loadAllFullWorkouts() {
            WorkoutService.ListExercise().then(
                response => {  
                   const workouts = response.data;
                    this.$store.commit("SET_UNIQUE_LIST_EXERCISE", workouts);
                }
            )
        },
        deleteExercise(workoutId) {
            WorkoutService.deleteExercise(workoutId).then(
                () => {
                    
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
        

