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
      };
    },
    methods: {
      register() {
        axios
          .post("/api/subscribe", {"username": this.username, "password": this.password, "email": this.email})
          .then((response) => {
            this.$router.push("/login");
          })
          .catch((error) => {
            console.log(error);
          });
        // Ici, vous pouvez ajouter votre logique de gestion de la connexion.
        // Par exemple, envoyer les données au serveur, vérifier les informations d'authentification, etc.
        // Une fois que l'utilisateur est connecté avec succès, vous pouvez rediriger vers la page d'accueil.
        // Vous pouvez également gérer les erreurs et afficher un message en conséquence.
        console.log("Nom d'utilisateur:", this.username);
        console.log("Mot de passe:", this.password);
        console.log("email:", this.email);
      },
    },
  };
  </script>
    
    <style scoped>
  label {
    color: white;
  }
  </style>
    