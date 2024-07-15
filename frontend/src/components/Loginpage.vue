<template>
  <!--prevent default submit method and use own defined method-->
  <form @submit.prevent="submitForm">
    <input v-model="formData.username" type="text" placeholder="Username" required>
    <input v-model="formData.password" type="password" placeholder="Password" required>
    <button type="submit">Submit</button>
  </form>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      formData: {
        username: '',
        password: ''
      }
    };
  },
  methods: {
    submitForm() {
      axios.post('http://localhost:8080/login', this.formData)
          .then(response => {
              // redirect to associated web page
              window.location.href = response.data.redirect;
          })
          .catch(error => {
            console.error('There was an error submitting the form: ', error.response.data);
            alert(error.response.data);  // Display the error message from the server
          });
    }
  }
}
</script>
