<template>
  <div class="container">
    <h1>Nom du salon</h1>
    <table>
      <tr v-for="message in userMessage" :key="message.id">
        <td>{{ message.content }}</td>
      </tr>
    </table>
  </div>
  <div class="bottom-input">
    <input
      type="text"
      v-model="content"
      @keydown.enter.prevent="addMessage(content)"
    />
  </div>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      content: "",
      userMessage: [
        { id: 1, content: "Test 1" },
        { id: 2, content: "Test 2" },
        { id: 3, content: "Test 3" },
        { id: 4, content: "Test 4" },
        { id: 5, content: "Test 5" },
        { id: 6, content: "Test 6" },
        { id: 7, content: "Test 7" },
        { id: 8, content: "Test 8" },
        { id: 9, content: "Test 9" },
        { id: 10, content: "Test 10" },
        { id: 11, content: "Test 11" },
        { id: 12, content: "Test 12" },
        { id: 13, content: "Test 13" },
        { id: 14, content: "Test 14" },
        { id: 15, content: "Test 15" },
        { id: 16, content: "Test 16" },
        { id: 17, content: "Test 17" },
        { id: 18, content: "Test 18" },
        { id: 19, content: "Test 19" },
        { id: 20, content: "Test 20" },
      ],
    };
  },
  methods: {
    addMessage(data) {
      axios
        .get("localhost:8080/salons")
        .then((response) => (this.info = response));
      console.log(data);
      this.content = "";
    },
  },
  mounted() {
    axios
      .get("/api/servers") // Remplacez par l'URL de votre endpoint
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.error("Erreur lors de la récupération des données :", error);
      });
  },
};
</script>
<style>
td {
  color: white;
}
.container {
  position: relative;
  min-height: 90vh; /* Assure que le conteneur occupe au moins la hauteur de la fenêtre visible */
}

.bottom-input {
  display: flex;
  justify-content: center;
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
}
input {
  padding: 20px;
  width: 35%;
  border-radius: 10px;
  border: none;
  background-color: #383a40;
  color: white;
}
input:focus {
  border: none; /* Supprime la bordure */
  outline: none; /* Supprime également l'effet d'outline lors du focus */
}
button {
  margin-left: 50px;
  padding: 10px;
}
</style>