import axios from 'axios';

export default {

  addExercise(user) {
    return axios.post('/workouts', user)
  }
}