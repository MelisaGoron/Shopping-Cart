<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="email"
      :rules="emailRule"
      label="email"
      required
    ></v-text-field>

    <v-text-field type = "password"
      v-model="password"
      :rules="passwordRule"
      label="password"
      required
    ></v-text-field>

    <v-btn
      color="success"
      class="mr-4"
      @click="login"
    >
      Login btn
    </v-btn>

    <v-btn
      color="success"
      class="mr-4"
      @click="newAcc"
    >
      Create account
    </v-btn>
  </v-form>
</template>

<script>
import axios from 'axios'
import Vue from 'vue'
export default {
  name: 'LoginItem',
  created () {
    console.log('Initialize connection')
  },
  data () {
    return {
      valid: false,
      email: '',
      password: '',
      emailRule: [
        (v) => !!(v) || 'E-mail is required',
        (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid'
      ],
      passwordRule: [
        (v) => !!v || 'Password is required'
      ],
      connection: null
    }
  },
  methods: {
    login () {
      this.$refs.form.validate()
      if (this.valid) {
        const URL = 'http://localhost:8080/login'
        axios.get(URL + '/user/' + this.email + '/' + this.password).then((response) => {
          console.log(response.data)
          this.$store.dispatch('login', response.data.id)
          this.$router.push({ name: 'UserHome' })
        }).catch(() => {
          axios.get(URL + '/admin/' + this.email + '/' + this.password).then((response) => {
            console.log(response.data)
            this.$store.dispatch('login', response.data.id)
            this.$router.push({ name: 'AdminHome' })
            .catch(() => {
              alert("Invalid Credentials")
            })
          })
        })
      }
    },
    newAcc () {
      this.$router.push({ name: 'Register' })
    }
  }
}

</script>
