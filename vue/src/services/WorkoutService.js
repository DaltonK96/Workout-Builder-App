import axios from 'axios';

export default {

  addExercise(user) {
    return axios.post('/workouts', user)
  },
  
  ListExercise() {
    return axios.get('/workouts/all')
  },

  exerciseById(id) {
    return axios.get('/workouts/' + id)
  },

  generateRandomExercise(user) {
    return axios.post('/randomWorkouts', user)
  },

  getRandomWorkout(id) {
    return axios.get('/workouts/randomlyGenerated/' + id)
  },

  editExercise(user,level){
    if(level=== "beginner"){
      return axios.post('/workouts/beginner',user)
    } else if( level === "intermediate"){
      return axios.post('/workouts/intermediate',user)
    } else{
      return axios.post('/workouts/extreme',user)
    }
 
    },
    deleteExercise(id){
      return axios.delete('/workouts/'+ id)
    }
  }
