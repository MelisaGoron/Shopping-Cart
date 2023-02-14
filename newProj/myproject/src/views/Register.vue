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
              ref="email"
              v-model="email"
              :rules="emailRule"
              label="E-mail"
              required
            ></v-text-field>

            <v-text-field
              ref="password"
              v-model="password"
              type="password"
              :rules="[
                () => !!password || 'This field is required',
              ]"
              label="Password"
              required
            ></v-text-field>

            <v-text-field
              ref="name"
              v-model="name"
              label="Name"
              type="text"
              :rules="[
                () => !!name || 'This field is required',
              ]"
              required
            ></v-text-field>

          </v-card-text>
          <v-divider class="mt-12"></v-divider>
          <v-card-actions>
            <v-btn text @click="cancel">
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
        email: "",
        password: "",
        name: "",
        formValid: false,
        loading: false,
        router_name: "LoginItem",
        emailRule: [
          v => !!v || 'E-mail is required',
          v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
        ]
      }
    },
    methods: {
      submit() {
        this.$refs.form.validate();
        if(this.formValid)
        {
          URL = "http://localhost:8080/new/user";


          this.loading = true;
          axios.post(URL, {
            name: this.name,
            email: this.email,
            password: this.password
          }).then((response) => {
                this.clearForm();
                this.$router.push({name: this.router_name});
                alert("Account created!");
              })
          .catch((err) => {
            alert("E-mail already taken");
            this.clearForm();
          }
          ).finally(() => {this.loading = false;});
          }
      },
      clearForm(){
        this.$refs.form.reset();
      },
      cancel()
      {
        this.$router.push({name: this.router_name});
      }
  }
  }
  </script>

