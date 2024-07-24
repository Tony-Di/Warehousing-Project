
<template>
  <table cellspacing="30">
    <thead>
    <tr>
      <th scope="col">Employee ID</th>
      <th scope="col">Employee Name</th>
      <th scope="col">Employee Age</th>
      <th scope="col">Employee Gender</th>
      <th scope="col">Employee Position</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="employee in userData" :key="employee.id">
      <td>{{ employee.id }}</td>
      <td>{{ employee.name }}</td>
      <td>{{ employee.age }}</td>
      <td>{{ employee.gender }}</td>
      <td>{{employee.position}}</td>
    </tr>
    </tbody>
  </table>

  <form @submit.prevent="addUser">
    <input type="text" v-model="newUser.name" placeholder="Name">
    <input type="number" v-model="newUser.age" placeholder="Age">
    <input type="text" v-model="newUser.gender" placeholder="Gender">
    <input type="text" v-model="newUser.position" placeholder="Position">
    <button type="submit">Add User</button>
  </form>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      userData: [],
      newUser: {
        name: '',
        age: '',
        gender: '',
        position: ''
      }
    };
  },
  created() {
    this.fetchUserData();
  },
  methods: {
    fetchUserData() {
      axios.get(`http://localhost:8080/allusers`)
          .then(response => {
            this.userData = response.data;
            console.log(this.userData)
          })
          .catch(error => {
            console.error('Error fetching user data:', error);
            alert('Failed to load user data.');// Display the error message from the server
          });
    },
    addUser() {
      if(this.newUser.name.toLowerCase() !== "admin" && this.newUser.position.toLowerCase() !== "admimstration" &&(this.newUser.position ==="manager"||this.newUser.position ==="stockinEmployee"||this.newUser.position ==="stockoutEmployee"))
      {
        axios.post('http://localhost:8080/adduser', this.newUser)
            .then(response => {
              alert('User added successfully!');
              this.newUser = { name: '', age: '', gender: '', position: '' }; // Reset form
              this.fetchUserData(); // Optionally re-fetch all users to update the list
            })
            .catch(error => {
              console.error('Error adding user:', error);
              alert('Failed to add user.');
            });
      }
      else
      {
        alert('User should not be admin. And position should be manager or stockinEmploee and manager');
        this.newUser = { name: '', age: '', gender: '', position: '' };
      }

    }
  }
}
</script>