<template>
  <v-container class="grey lighten-5">
    <h3 align="center">User Home</h3>
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
           @click="deleteAccount()"
        >
          <h3>Delete account</h3>
     </v-btn>
     <v-spacer></v-spacer>
     <v-btn
          icon
           @click="getOrders()"
        >
          <h3>Show orders</h3>
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
      >
      <v-form ref = "form">
      <v-card-actions >
        <v-btn
          icon
          @click="show = !show"
        >
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>
      <v-expand-transition>
        <div v-show="show">

          <v-card-text>

        <div class="text-center mt-12">
           <v-text-field
              ref="quantity"
              v-model="quantity"
              min="1"
              label="Quantity"
              required
            ></v-text-field>
        </div>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions class="justify-space-between">
        <v-slide-x-reverse-transition>
              <v-tooltip
                v-if="formHasErrors"
                left
              >
                <template v-slot:activator="{ attrs }">
                  <v-btn
                    icon
                    :loading = "loading"
                    class="my-0"
                    v-bind="attrs"
                    @click="buyProduct(product.id)"
                  >
                    <v-icon>mdi-refresh</v-icon>
                  </v-btn>
                </template>
                <span>Refresh form</span>
              </v-tooltip>
            </v-slide-x-reverse-transition>
            <v-btn
              color="primary"
              text
              @click="buyProduct(product.id)"
            >
              Buy product
            </v-btn>
      </v-card-actions>
        </div>
      </v-expand-transition>
    </v-form>
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
  export default {
    name: "UserHome",
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
      buyProduct(idForSelectedProduct)
      {

        URL = `http://localhost:8080/attach/UPtoO`;
        this.loading = true;
        axios.post(URL, {
            idProduct: idForSelectedProduct,
            idUser: this.$store.state.userId,
            quantity: this.quantity
          }).then((response) => {
                alert("Bought!");
                this.quantity = 1;
              })
          .catch((err) => {
            alert("Can t buy");
          }
          ).finally(() => {this.loading = false;});
      },
      deleteAccount()
      {
        const URL = `http://localhost:8080/delete/user/${this.$store.state.userId}`;
         axios
          .delete(URL)
          .then(() => {
            alert("Account deleted successfully");
            this.$router.push({name: "Register"});
          })
          .catch((err) => {alert("Could not delete account");});
      },
      getOrders()
      {
        this.$router.push({name: "GetOrders"});
      },
      Logout()
      {
        this.$router.push({name: "LoginItem"});
      }
    },
  };
  </script>