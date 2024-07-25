<template>
  <form @submit.prevent="submitForm">
    <table cellspacing="30">
      <thead>
      <tr>
        <th scope="col">Shelves ID</th>
        <th scope="col">Location of Shelve</th>
        <th scope="col">name of goods</th>
        <th scope="col">quantity</th>
        <th scope="col">price</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in sorteditemsData" :key="item.id">
        <td>{{ item.shelvesId }}</td>
        <td>{{ item.location_of_shelves }}</td>
        <td>{{ item.name_of_goods }}</td>
        <td><input type="number" min="0" v-model="item.count_of_goods"></td>
        <td>{{ item.price_of_goods }}</td>
        <td><button @click="updateQuantity(item)">Update</button></td>
      </tr>
      </tbody>
    </table>
  </form>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      formData: {
        username: '',
        password: ''
      },
      items:[],
    };
  },
computed: {
  // Computed property to sort shelfData by shelvesId
  sorteditemsData() {
    return [...this.items].sort((a, b) => a.shelvesId - b.shelvesId);
  }
},
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios.get('http://localhost:8080/instock')
          .then(response => {
            this.items = response.data.map(item => ({
              ...item,
              original_count_of_goods: item.count_of_goods
            }));
          })
          .catch(error => {
            console.error('Error fetching data: ', error);
          });
    },
    submitForm() {

    },
    updateQuantity(item) {
      if (item.count_of_goods > item.original_count_of_goods) {
        axios.post(`http://localhost:8080/update-quantity/${item.shelvesId}/${item.name_of_goods}/${item.count_of_goods}`)
            .then(response => {
              alert('Quantity updated successfully!');
              item.original_count_of_goods = item.count_of_goods;
            })
            .catch(error => {
              console.error('Failed to update quantity: ', error);
              item.count_of_goods = item.original_count_of_goods;
              alert('Failed to update quantity!');
            });
      } else {
        alert('New quantity must be greater than the original quantity.');
        item.count_of_goods = item.original_count_of_goods;
      }
    }
  }
}
</script>