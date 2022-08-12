import axios from 'axios';

export default {

  addExercise(user) {
    return axios.post('/workouts', user)
  },

  editExercise(user,level){
    if(level=== "beginner"){
      return axios.post('/workouts/beginner',user)
    } else if( level === "intermediate"){
      return axios.post('/workouts/intermediate',user)
    } else{
      return axios.post('/workouts/extreme',user)
    }

    }
  }
