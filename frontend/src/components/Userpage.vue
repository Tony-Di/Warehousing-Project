<template>
  <div>
    <h1>User Profile</h1>
    <p>Username: {{ userData.name}}</p>
    <p>Age: {{ userData.age }}</p>
    <p>Gender: {{ userData.gender }}</p>
    <p>Position: {{ userData.position }}</p>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      userData: {}
    };
  },
  // after the data created userData object it will immediately call this function
  created() {
    this.fetchUserData();
  },
  methods: {
    fetchUserData() {
      // get the user id from "/userpage/:id"
      const userId = this.$route.params.id;
      axios.get(`http://localhost:8080/userpage/${userId}`)
          .then(response => {
            // assign the return data to local object
            this.userData = response.data;
          })
          .catch(error => {
            console.error('Error fetching user data:', error);
            alert('Failed to load user data.');// Display the error message from the server
          });
    }
  }
}
</script>
