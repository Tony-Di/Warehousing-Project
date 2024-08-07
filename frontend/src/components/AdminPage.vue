
<template>
  <p>Welcome to admin page</p>
  <button @click="logout">Logout</button>
  <nav>
    <a href="/stockin" style="margin-right: 20px;">stockin</a>
    <a href="/stockout" style="margin-right: 20px;">stockout</a>
    <a href="/manager" style="margin-right: 20px;">manager</a>
  </nav>
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
    <input type="text" required v-model="newUser.name" placeholder="Name">
    <input type="number" required v-model="newUser.age" placeholder="Age">
    <input type="text" required v-model="newUser.gender" placeholder="Gender">
    <select v-model="newUser.position" required>
      <option disabled value="">Select a Position</option>
      <option>manager</option>
      <option>stockinEmployee</option>
      <option>stockoutEmployee</option>
    </select>
    <button type="submit">Add User</button>
  </form>
</template>

<script>
import axios from 'axios';
import {jwtDecode} from "jwt-decode";
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
    this.checkRole();
  },
  methods: {
    checkRole(){
      const token = localStorage.getItem('token');
      if(token)
      {
        const decoded = jwtDecode(token);
        if ( decoded.role !== 'administration') {
          console.error("Role does not match required permissions.");
          this.$router.push("/login")
        }
        else
        {
          this.fetchUserData()
        }
      }
      else
      {
        console.error("Role does not match required permissions.");
        this.$router.push('/login');
      }
    },
    fetchUserData() {
      const token = localStorage.getItem('token');
      if(token)
      {
        const decoded = jwtDecode(token);
        if (decoded.role !== 'administration') {
          this.$router.push("/login")
        }
      }
      else
      {
        this.$router.push('/login');
      }
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

    },
    logout() {
      localStorage.removeItem('token');  // Remove the token
      this.$router.push('/login');  // Redirect to login page
    }
  }
}
</script>