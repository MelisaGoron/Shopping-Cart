<template>

  <v-container class="grey lighten-5">
     <v-spacer></v-spacer>
     <v-btn
          icon
           @click="Logout()"
        >
          <h3>Logout</h3>
     </v-btn>
     <v-spacer></v-spacer>
     <v-btn
          icon
           @click="Back()"
        >
          <h3>Back</h3>
     </v-btn>
     <v-spacer></v-spacer>
     <h4 align="left">Orders:</h4>
      <v-row>
        <v-col cols = "8">
         <v-row>
           <v-col cols = "12" lg = "4" md = "6" xs = "12"
          v-for="order in orders"
          :key="order.productName"      >
         <v-card
      class="mx-auto"
      max-width="344"
    >
      <v-img
        src="https://images.unsplash.com/photo-1609780447631-05b93e5a88ea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZnJ1aXQlMjBzaG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80"
        height="200px"
      ></v-img>

      <v-card-title>

        {{ order.productName }}

      </v-card-title>

      <v-card-subtitle>
        Price: {{ order.productPrice }}<br>
        Quantity: {{ order.productQuantity}}<br>
      </v-card-subtitle>

          </v-card>
        </v-col>
      </v-row>
        </v-col>
      </v-row>
      <h3>Total amount to pay: {{this.priceTotalToPay}}</h3>

    </v-container>

  </template>

  <script>
  // @ is an alias to /src

  import axios from "axios";
  export default {
    name: "UserHome",
    created() {
      console.log('Initialize connection')
      this.getOrders();
      this.getSumOfPrices();
    },
    data() {
      return {
        show: false,
        orders: [],
        priceTotalToPay : 0,
      };
    },
    methods: {
      getOrders() {
        const URL = `http://localhost:8080/order/id/${this.$store.state.userId}`;
        axios
          .get(URL)
          .then((response) => {
            console.log(response);
            this.orders = response.data;

          })
          .catch((err) => {});
      },
      Logout()
      {
        this.$router.push({name: "LoginItem"});
      },
      Back()
      {
        this.$router.push({name: "UserHome"});
      },
      getSumOfPrices(){
        const URL = `http://localhost:8080/totalPrice/${this.$store.state.userId}`;
        axios
          .get(URL)
          .then((response) => {
            console.log(response);
            this.priceTotalToPay = response.data;

          })
          .catch((err) => {});
      }
    },
  };
  </script>