<template>
    <v-container class="grey lighten-5">
      <h3 align="center">Admin Home</h3>
      <v-btn
          icon
           @click="Logout()"
        >
          <h3>Logout</h3>
     </v-btn>
     <v-spacer></v-spacer>
       <v-btn
          icon
           @click="deleteAccount()"
        >
          <h3>Delete account</h3>
        </v-btn>
       <v-spacer></v-spacer>
       <v-btn
          icon
           @click="addProduct()"
        >
          <h3>Add product</h3>
        </v-btn>
         <v-spacer></v-spacer>
         <v-btn
          icon
           @click="deleteProduct()"
        >
          <h3>Delete product</h3>
        </v-btn>

        <v-spacer></v-spacer>
        <h4 align="left">Products:</h4>
      <v-row>
        <v-col cols = "8">
         <v-row>
           <v-col cols = "12" lg = "4" md = "6" xs = "12"
          v-for="product in products"
          :key="product.id"      >
         <v-card
      class="mx-auto"
      max-width="344"
    >
      <v-img
        src="https://images.unsplash.com/photo-1609780447631-05b93e5a88ea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZnJ1aXQlMjBzaG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80"
        height="200px"
      ></v-img>

      <v-card-title>

        {{ product.name }}

      </v-card-title>

      <v-card-subtitle>
        Price: {{ product.price }}<br>
        Quantity: {{ product.quantity}}<br>
      </v-card-subtitle>

          </v-card>
        </v-col>
      </v-row>
        </v-col>
      </v-row>


    </v-container>
  </template>

  <script>
  // @ is an alias to /src
  import axios from "axios";
  import { mapGetters } from "vuex";
  export default {
    name: "AdminHome",
    created() {
      console.log('Initialize connection')
      this.getProducts();
    },

    data() {
      return {
        show: false,
        products: [],
        quantity: 1,
        formValid: false,
        loading: false,
      };
    },
    methods: {
      getProducts() {
        const URL = 'http://localhost:8080/all_products';
        axios
          .get(URL)
          .then((response) => {
            console.log(response);
            this.products = response.data;

          })
          .catch((err) => {});
      },
      addProduct()
      {
        this.$router.push({name: "AddProduct"});
      },
      deleteProduct() {
       this.$router.push({name: "DeleteProduct"});
      },
      deleteAccount()
      {
        const URL = `http://localhost:8080/delete/admin/${this.$store.state.userId}`;
         axios
          .delete(URL)
          .then(() => {
            alert("Account deleted successfully");
            this.$router.push({name: "Register"});
          })
          .catch((err) => {alert("Could not delete account");});
      },
      Logout()
      {
        this.$router.push({name: "LoginItem"});
      }
    },
    computed: {
      ...mapGetters(["getId"]),
    },
  };
  </script>
