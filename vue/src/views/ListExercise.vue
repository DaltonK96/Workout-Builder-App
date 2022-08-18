<template>
  <div>
    <h1> List of Exercises </h1>
      
      <table>

          <thead>
              <tr>
                
              </tr>
          </thead>
          <tbody>
              <tr v-for="(workout,index) in $store.state.uniqueWorkouts" v-bind:key="index" id="tableExercise">
                  <td id="workoutId">{{workout.workoutId}}</td>
                  <td id="workoutName">{{workout.workoutName}}</td>
                  <td id="workoutDesc">{{workout.workoutDesc}}</td>
                  <td id="expectedTarget">{{workout.expectedTarget}}</td>
                  <td id="weight">{{workout.weight}}</td>
                  <td id="repSet">{{workout.repSet}}</td>
                  <td id="time">{{workout.time}}</td>
                  
                  <td>
                       
                      <router-link v-bind:to="{name: 'EditExercise', params: {workoutId: workout.workoutId}}">Edit</router-link>
                      &nbsp; &nbsp;
    
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
        

