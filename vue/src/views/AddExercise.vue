<template>
  <div id="add-exercise">
      <form class="form-add-exercise" @submit.prevent="addExercise" >
  <h1 id="add-exercise-test" class="h3 mb-3 font-weight-normal">Add Exercise </h1>

  <label for="workoutName"> WorkoutName</label>
  <input
  type="text"
  id="workoutName"
  form="form-control"
  placeholder="workoutName"
  v-model="workout.workoutName"
  required
  autofocus
  />

 <label for="workoutDesc"> WorkoutDesc</label>
  <input
  type="text"
  id="workoutDesc"
  form="form-control"
  placeholder="workoutDesc"
  v-model="workout.workoutDesc"
  required
  autofocus
  />

   <label for="expectedTarget"> expectedTarget</label>
  <input
  type="text"
  id="expectedTarget"
  form="form-control"
  placeholder="expectedTarget"
  v-model="workout.expectedTarget"
  required
  autofocus
  />
<router-link :to="{name:'AddExercise'}">Add new exercise?</router-link>
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
    method: {
        addExercise(){
            if(this.workout.workoutName != this.workout.confirmworkoutName){
                this.addExerciseErrors = true;
                this. addExerciseErrorMsg = 'Exercise is not an option. ';
            }else{
               WorkoutService 
            }
        }

    }
}
</script>

