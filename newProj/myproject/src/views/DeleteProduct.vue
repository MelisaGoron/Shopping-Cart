<template>
    <v-container class="grey lighten-5">
      <v-btn
          icon
           @click="Back()"
        >
          <h3>Back</h3>
     </v-btn>
      <v-row>
        <v-col cols="8">
          <v-row>
            <v-col
              cols="12"
              lg="4"
              md="6"
              xs="12"
              v-for="product in products"
              :key="product.id"
            >
              <v-card class="mx-auto" max-width="344">
                <v-img
                  src="https://images.unsplash.com/photo-1609780447631-05b93e5a88ea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZnJ1aXQlMjBzaG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80"
                  height="200px"
                ></v-img>

                <v-card-title>
                  {{ product.name }}
                </v-card-title>

                <v-card-subtitle>
                  Price: {{ product.price }}<br />
                  Quantity: {{ product.quantity }}
                </v-card-subtitle>
                <v-card-actions>
                  <v-btn
                  color="orange lighten-2"
                  text
                  @click="deleteProduct(product.id)"
                  >
                  Delete product
                </v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
      </v-row>

      <v-snackbar
        v-model="snackbar"
        :timeout="timeout"
      >
        {{ text }}

        <template v-slot:action="{ attrs }">
          <v-btn
            color="blue"
            text
            v-bind="attrs"
            @click="snackbar = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>

    </v-container>
  </template>


  <script>
  // @ is an alias to /src
  import axios from "axios";
  import { mapGetters } from "vuex";
  export default {
    name: "DeleteProduct",
    created() {
      this.getProducts();
    },
    data() {
      return {
        products: [],
        snackbar: false,
        text: "",
        timeout: 5000,
      };
    },
    methods: {
      getProducts() {
        const URL = `http://localhost:8080/all_products`;
        axios
          .get(URL)
          .then((response) => {
            console.log(response);
            this.products = response.data;

          })
          .catch((err) => {});
      },
      deleteProduct(productId)
      {
        const URL = `http://localhost:8080/delete/product/${productId}`;
         axios
          .delete(URL)
          .then(() => {
            this.getProducts();
          })
          .catch((err) => {});
      },
      Back()
      {
        this.$router.push({name: "UserHome"});
      },
    },
    computed: {
      ...mapGetters(["getId"]),
    },
  };
  </script>
