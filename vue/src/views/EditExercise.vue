<template>
  <div id="edit-exercise">
      <form class="form-edit-exercise" @submit.prevent="editExercise">
          <div class="edit-Exercise">
  <h1 id="edit-exercise-test" class="h3 mb-3 font-weight-normal">edit Exercise </h1>

</div>

<div>Difficulty Level</div>
<select name="difficulty_level" id="difficulty_level" v-model="level">
  <optgroup label="Difficulty Level">
    <option value="beginner">Beginner</option>
    <option value="intermediate">Intermediate</option>
    <option value="extreme">Extreme</option>
  </optgroup>
</select>


</template>

<script>
import WorkoutService from '../services/WorkoutService';
export default {
     name: "editExercise",
    data(){
        return {
            level:'',
            difficultyLevel: {
                weight: '',
                repSet: '',
                expectedTime: '',
            },
            editExerciseErrors: false,
            editExerciseErrorMsg: 'There was a problem adding level',
        };

},
methods: {
        editExercise(){
         WorkoutService
            .editExercise(this.workout,this.level)
            .then((response) => {
                if(response.status ==201){
                this.$router.push({
                    path:'/workout/'+ this.level,
                    query:{ WorkoutService: 'success'},
                });
            }
        })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.editExerciseErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
}
}

</script>
