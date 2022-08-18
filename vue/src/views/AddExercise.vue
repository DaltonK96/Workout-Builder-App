<template>
  <div id="add-exercise">
      <form v-on:submit.prevent="addExercise" class="form-add-exercise">
          <div class="add-Exercise">
  <h1 id="add-exercise-test" class="h3 mb-3 font-weight-normal">Add Exercise </h1>

</div>
  <label for="workoutName"> Workout Name </label>
  <input
  type="text"
  id="workoutName"
  class="add-ex-form"
  v-model="workout.workoutName"
  required
  autofocus
  />

 <label for="workoutDesc"> Workout Description </label>
  <input
  type="text"
  id="workoutDesc"
  class="add-ex-form"
  v-model="workout.workoutDesc"
  required
  autofocus
  />

<div>Target Area</div>
<select name="expected_target" id="expected_target" v-model="workout.expectedTarget">
  <optgroup label="Target Area">
    <option value="arms">Arms</option>
    <option value="legs">Legs</option>
    <option value="abs">Abs</option>
    <option value="back">Back</option>
    <option value="cardio">Cardio</option>
    <option value="total body">Total Body</option>
  </optgroup>
</select>

<button class="btn btn-lg btn-primary btn-block" type="add">
    Add Workout
</button>
      </form>
      </div>
    
</template>

<script>
import WorkoutService  from "../services/WorkoutService";


export default {
    name: "addExercise",
    data(){
        return {
            workout: {
                workoutName: '',
                workoutDesc: '',
                expectedTarget: '',
            },
            addExerciseErrors: false,
            addExerciseErrorMsg: 'There was a problem adding workout',

        };
    },
    methods: {
      
        addExercise(){
         WorkoutService 
            .addExercise(this.workout)
            .then((response) => {
                if(response.status ==200){
                  this.$router.push({name: "ListExercise"})
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
                    this.$router.push({name: "ListExercise"});
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

