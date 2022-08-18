<template>
  <div id="generate-random">
      <form class="form-generate-random" @submit.prevent="generateRandomExercise" >
          <div class="generate-random">
  <h1 id="generate-random-test" class="h3 mb-3 font-weight-normal">Generate Random Workout</h1>

</div>
<div>Select Difficulty</div>
  <select name="difficulty" id="difficulty" v-model="workout.difficulty">
  <optgroup label="difficulty">
    <option value="beginner">Beginner</option>
    <option value="intermediate">Intermediate</option>
    <option value="extreme">Extreme</option>
  </optgroup>
  </select>

 <label for="time">Expected Time Frame (min)</label>
  <input
  type="text"
  id="workoutDesc"
  class="gen-ex-form"
  v-model="workout.time"
  required
  autofocus
  />

<div>Target Area</div>
<select name="target" id="target" v-model="workout.target">
  <optgroup label="Target">
    <option value="arms">Arms</option>
    <option value="legs">Legs</option>
    <option value="abs">Abs</option>
    <option value="back">Back</option>
    <option value="cardio">Cardio</option>
    <option value="total body">Total Body</option>
  </optgroup>
</select>

<button  type="add">
    Generate Workout
</button>
      </form>
      </div>
    
</template>

<script>

import WorkoutService  from "../services/WorkoutService";

export default {name: "generateRandomExercise",
    data(){
        return {
            workout: {
                difficulty: '',
                time: '',
                target: '',
            },
            addExerciseErrors: false,
            addExerciseErrorMsg: 'There was a problem generating workout',

        };
    },
    methods: {
      
        generateRandomExercise(){
         WorkoutService 
            .generateRandomExercise(this.workout)
            .then((response) => {
                if(response.status ==200){
                    alert(response.data)
                  this.$router.push({name: "ListRandom", params: {randomId:response.data}})
                /*this.$router.push({
                    path:'/workout',
                    query:{ WorkoutService: 'success'},
                });*/
            }
        })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.addExerciseErrorMsg = 'Bad Request: Validation Errors';
            }
          });
        },
          saveWorkout() {

            WorkoutService.saveWorkout(this.workout).then(
                () => {
                    this.$router.push({name: "ListRandom"});
                }
            ).catch(
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

