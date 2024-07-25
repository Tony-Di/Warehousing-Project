<template>
  <p>Welcome to manager page</p>
  <table>
    <thead>
    <tr>
      <th scope="col">Shelves ID</th>
      <th scope="col">name of goods</th>
      <th scope="col">price of goods</th>
      <th scope="col">Location of Shelf</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="shelf in sortedShelfDataTable" :key="shelf.id">
      <td>{{ shelf.shelvesId }}</td>
      <td><input type="text" v-model="shelf.name_of_goods"></td>
      <td><input type="number" min="0" v-model="shelf.price_of_goods"></td>
      <td><input type="text" v-model="shelf.location_of_shelves"></td>
      <td><button @click="updateShelfDetail(shelf)">Update</button></td>
    </tr>
    </tbody>
  </table>

  <p>Add new shelf below</p>
  <form @submit.prevent="addShelf">
    <input type="text" required v-model="newShelf.locationofshelves" placeholder="location of shelf">
    <button type="submit">Add Shelf</button>
  </form>
  <div>
  <p>add goods to an existed shelf below</p>
  <form @submit.prevent="addGoods">
    <select v-model="newgoods.shelfId" required>
      <option disabled value="">Select a Shelf ID</option>
      <option  v-for="shelf in  uniqueShelfIds"  :key="shelf.shelvesId" :value="shelf.shelvesId">
        {{ shelf.shelvesId }}
      </option>
    </select>
    <input type="text" v-model="newgoods.nameofgoods" placeholder="Name of goods">
    <input type="number" v-model="newgoods.priceofgoods" placeholder="price of goods">
    <button  type="submit">Add Goods</button>
  </form>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      shelfData: [],
      newShelf: {
        nameofgoods:'',
        locationofshelves: '',
      },
      newgoods: {
        shelfId:'',
        nameofgoods: '',
        priceofgoods:''
      },
    };
  },
  computed: {
    // Computed property to sort shelfData by shelvesId
    uniqueShelfIds() {
      const unique = new Map();
      this.shelfData.forEach(shelf => {
        if (!unique.has(shelf.shelvesId)) {
          unique.set(shelf.shelvesId, shelf);
        }
      });
      return Array.from(unique.values());
    },
    sortedShelfDataTable() {
      return [...this.shelfData].sort((a, b) => a.shelvesId - b.shelvesId);
    }
  },
  created() {
    this.fetchShelfData();
  },
  methods: {
    fetchShelfData() {
      axios.get(`http://localhost:8080/instock`)
          .then(response => {
            this.shelfData = response.data.map(shelf => ({
              ...shelf,
              originalNameOfGoods: shelf.name_of_goods
            }));
            console.log(this.shelfData);
          })
          .catch(error => {
            console.error('Error fetching shelf data:', error);
            alert('Failed to load shelf data.');// Display the error message from the server
          });
    },
    addShelf() {
        axios.post(`http://localhost:8080/addshelf`, this.newShelf)
            .then(response => {
              alert('Shelf added successfully!');
              this.newShelf = { locationofshelves: ''};
              console.log(this.newShelf.locationofshelves)// Reset form
              this.fetchShelfData();
            })
            .catch(error => {
              this.newShelf = { locationofshelves: ''};
              console.error('Error adding shelf:', error);
              alert('Failed to add shelf.');
            });
      },
    addGoods() {
      axios.post(`http://localhost:8080/addgoods/${this.newgoods.shelfId}/${this.newgoods.nameofgoods}/${this.newgoods.priceofgoods}`)
          .then(response => {
            alert('Goods added successfully!');
            this.newgoods = { nameofgoods: '', priceofgoods: ''};
            console.log(this.newgoods.locationofshelves)// Reset form
            this.fetchShelfData();
          })
          .catch(error => {
            console.error('Error adding goods:', error);
            alert('Failed to add goods.');
          });
    },
    updateShelfDetail(shelf) {
      console.log(shelf.name_of_goods)
      console.log(shelf.location_of_shelves)
      axios.post(`http://localhost:8080/updateShelf/${shelf.shelvesId}/${shelf.name_of_goods}/${shelf.location_of_shelves}/${shelf.price_of_goods}/${shelf.originalNameOfGoods}`)
          .then(response => {

            alert('Shelf updated successfully!');
          })
          .catch(error => {
            console.error('Failed to update shelf: ', error);
            alert('Failed to update shelf!');
          });
    }
  }
}
</script>