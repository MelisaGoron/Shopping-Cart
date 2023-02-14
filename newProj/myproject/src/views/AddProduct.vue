<template>
    <v-row justify="center">
      <v-col
        cols="12"
        sm="10"
        md="8"
        lg="6"
      >
      <v-form ref = "form" v-model = "formValid">
        <v-card>
          <v-card-text>
             <v-text-field
              ref="name"
              v-model="name"
              type="text"
              :rules="[
                () => !!name || 'This field is required',
              ]"
              label="Name"
              required
            ></v-text-field>

            <v-text-field
              ref="price"
              v-model="price"
              type="number"
              :rules="[
                () => !!price || 'This field is required',
              ]"
              label="Price"
              required
            ></v-text-field>

            <v-text-field
              ref="quantity"
              v-model="quantity"
              label="Quantity"
              type="number"
              step = "any"
              :rules="[
                () => !!quantity || 'This field is required',
              ]"
              required
            ></v-text-field>>

          </v-card-text>
          <v-divider class="mt-12"></v-divider>
          <v-card-actions>
            <v-btn text @click="clearForm">
              Cancel
            </v-btn>
            <v-spacer></v-spacer>
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
                    @click="submit"
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
              @click="submit"
            >
              Submit
            </v-btn>
          </v-card-actions>
        </v-card>

      </v-form>
      </v-col>
    </v-row>
  </template>

  <script>
  // @ is an alias to /src
  import axios from 'axios';
  export default {
    name: 'Register',

    data() {
      return {
        name: "",
        price: 0.0,
        quantity: 0,
        formValid: false,
        loading: false,
        router_name: "AdminHome",
      }
    },
    methods: {
      submit() {
        this.$refs.form.validate();
        if(this.formValid)
        {
          URL = "http://localhost:8080/new/product";
          this.router_name = "AdminHome";

          this.loading = true;
          axios.post(URL, {
            name: this.name,
            price: this.price,
            quantity: this.quantity,
          }).then((response) => {
                this.clearForm();
                this.$router.push({name: this.router_name});
              })
          .catch((err) => {
            this.clearForm();
          }
          ).finally(() => {this.loading = false;});
          }
      },
      clearForm(){
        this.$refs.form.reset();
      }
  }
  }
  </script>

