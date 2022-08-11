<template>
  <div id="add-exercise">
      <form class="form-add-exercise" @submit.prevent="addExercise" >
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




<!--<input type="checkbox" id="arms" value="arms" v-model="workout.expectedArea">
<label for="arms">Arms</label>

<input type="checkbox" id="legs" value="legs" v-model="workout.expectedArea">
<label for="legs">Legs</label>

<input type="checkbox" id="abs" value="abs" v-model="workout.expectedArea">
<label for="abs">Abs</label>

<input type="checkbox" id="back" value="back" v-model="workout.expectedArea">
<label for="back">Back</label>

<input type="checkbox" id="cardio" value="cardio" v-model="workout.expectedArea">
<label for="cardio">Cardio</label>

<input type="checkbox" id="totalBody" value="totalBody" v-model="workout.expectedArea">
<label for="totalBody">Total Body</label> -->

  <!-- <label for="expectedTarget"> expectedTarget</label>
  <input
  type="dropdown"
  id="expectedTarget"
  class="add-ex-form"
  placeholder="expectedTarget"
  v-model="workout.expectedTarget"
  required
  autofocus
  />
  -->

<button v-on:click="addExercise" class="btn btn-lg btn-primary btn-block" type="add">
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
                if(response.status ==201){
                this.$router.push({
                    path:'/workout',
                    query:{ WorkoutService: 'success'},
                });
            }
        })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.addExerciseErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }

    }
}
</script>

