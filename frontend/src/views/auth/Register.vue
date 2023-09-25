<template>
    <div>
      <h1>Register</h1>
      <form @submit.prevent="register">
        <div class="form-group">
          <label for="username">Nom d'utilisateur:</label>
          <input
            type="text"
            id="username"
            v-model="username"
            class="form-control"
            required
          />
        </div>
        <div class="form-group">
          <label for="displayName">Nom:</label>
          <input
            type="text"
            id="displayName"
            v-model="displayName"
            class="form-control"
            required
          />
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input
            type="text"
            id="email"
            v-model="email"
            class="form-control"
            required
          />
        </div>
        <div class="form-group">
          <label for="password">Mot de passe:</label>
          <input
            type="password"
            id="password"
            v-model="password"
            class="form-control"
            required
          />
        </div>
        <button type="submit" class="btn btn-primary">S'inscrire</button>
        <button @click="$router.go(-1)" class="btn btn-primary">Retour</button>
      </form>
    </div>
  </template>
    
    <script>
  import axios from "axios";
  export default {
    data() {
      return {
        username: "",
        password: "",
        email: "",
        displayName: "",
      };
    },
    methods: {
      register() {
        axios
          .post("/api/subscribe", {"username": this.username, "password": this.password, "email": this.email, "displayName":displayName})
          .then((response) => {
            this.$router.push("/login");
          })
          .catch((error) => {
            console.log(error);
          });
      },
    },
  };
  </script>
    
    <style scoped>
  label {
    color: white;
  }
  </style>
    