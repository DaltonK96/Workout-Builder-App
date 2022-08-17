<template>
  <div id="edit-exercise">
      <form class="form-edit-exercise" v-on:submit.prevent="">
          <div class="edit-Exercise">
  <h1 id="edit-exercise-test" class="h3 mb-3 font-weight-normal">Edit Exercise</h1>

</div>

<div>

  <h2>{{workout.workoutName}}</h2>
  

  <label for= "difficultyLevel">Difficulty Level</label>

<select name="difficulty_level" id="difficulty_level" v-model="level">
  <optgroup label="Difficulty Level">
    <option value="beginner">Beginner</option>
    <option value="intermediate">Intermediate</option>
    <option value="extreme">Extreme</option>
  </optgroup>
</select>
</div>

<label for="weight"> Weight </label>
<input
  type="text"
  id="weight"
  class="edit-ex-form"
  v-model="difficultyLevel.weight"
  required
  autofocus
  />

  <label for="repSet">Sets and Reps</label>
<input
  type="text"
  id="repSet"
  class="edit-ex-form"
  v-model="difficultyLevel.repSet"
  required
  autofocus
  />

  <label for="expectedTime"> Expected Time </label>
<input
  type="text"
  id="expectedTime"
  class="edit-ex-form"
  v-model="difficultyLevel.expectedTime"
  required
  autofocus
  />
<button v-on:click="editExercise" class="btn btn-lg btn-primary btn-block" type="add">
    Edit Difficulty
</button>
      </form>
</div>
</template>

<script>
import WorkoutService from '../services/WorkoutService';
export default {
     name: "editExercise",
    data(){
        return {
            
            level: '',
            workout:{},
            difficultyLevel: {
                weight: '',
                repSet: '',
                expectedTime: '',
            },
            editExerciseErrors: false,
            editExerciseErrorMsg: 'There was a problem adding level',
        };

},
created(){
WorkoutService.exerciseById(this.$route.params.workoutId).then(
  response => {
    this.workout = response.data;
  }
)


},
methods: {
        editExercise(){
          this.difficultyLevel.workoutId=this.workout.workoutId;
         WorkoutService
            .editExercise(this.difficultyLevel,this.level)
            .then((response) => {
                if(response.status ==200){
                  this.$router.push({name: "ListExercise"})
                /*this.$router.push({
                    path:'/workout/'+ this.level,
                    query:{ WorkoutService: 'success'},
                });*/
            }
        })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.editExerciseErrorMsg = 'Bad Request: Validation Errors';
            }
          });
          
        },
        
        
}
}

</script>
