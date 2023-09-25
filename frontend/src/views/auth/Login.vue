<template>
  <div>
    <h1>Login</h1>
    <form @submit.prevent="login">
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
        <label for="password">Mot de passe:</label>
        <input
          type="password"
          id="password"
          v-model="password"
          class="form-control"
          required
        />
      </div>
      <div class="registerOrConnected">
        <a href="/register">Si vous n'avez pas de compte inscrivez-vous !</a>
        <button type="submit" class="btn btn-primary">Se connecter</button>
      </div>
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
    };
  },
  methods: {
    getUser() {
      axios.get("api/user?username=" + this.username).then((response) => {
        localStorage.setItem("user", JSON.stringify(response.data));
      });
    },
    login() {
      axios
        .post("/api/login", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          this.getUser();
          localStorage.setItem("token", response.data);
          this.$router.push("/");
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
.registerOrConnected {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  margin-top: 20px;
  width: 25%;
}
</style>
  