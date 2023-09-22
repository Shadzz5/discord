<template>
  <div class="containerChat">
    <h1>{{salon.name}}</h1>
    <table>
      <tr v-for="message in userMessage" :key="message.id">
        <td>{{ message.content }}</td>
      </tr>
    </table>
  </div>
  <div class="bottom-input">
    <input
    class="inputMessage"
      type="text"
      v-model="content"
      @keydown.enter.prevent="addMessage(content)"
    />
  </div>
</template>
<script>

import axios from "axios";

export default {
  props: ["salon"],
  data() {
    return {
      content: "",
      userMessage: [],
    };
  },
  watch: {
    salon: {
      handler: function () {
      },
      immediate: true,
    },
  },
  methods: {
    addMessage(data) {
      axios
        .get("/api/messages/add", {text: content, salonId: this.salon.id, user_id: 1})  
        .then((response) => (this.info = response));
      console.log(data);
      this.content = "";
    },
  },
 
};
</script>
<style>
td {
  color: white;
}
.containerChat {
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
.inputMessage {
  padding: 20px;
  width: 35%;
  border-radius: 10px;
  border: none;
  background-color: #383a40;
  color: white;
}
.inputMessage:focus {
  border: none; /* Supprime la bordure */
  outline: none; /* Supprime également l'effet d'outline lors du focus */
}
</style>