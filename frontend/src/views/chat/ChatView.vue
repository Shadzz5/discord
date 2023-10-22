<template>
  <div class="containerChat">
    <h1>{{ salon.name }}</h1>
      <div v-for="message in userMessage" :key="message.id">
        <p class="date">{{ formatDate(message.createdAt) }}</p>
        <p class="text">{{ message.text }}</p>
      </div>
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
import moment from "moment";
import VueNativeSock from 'vue-native-websocket'
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
        this.fetchMessages();
      },
      immediate: true,
    },
  },
  mounted() {
    this.fetchMessages();
  },
  methods: {
    sendMessage() {
      console.log('test')
      this.$socket.send("Votre message");
    },
    fetchMessages() {
      axios
        .get("api/messages/all?salonId=" + this.salon.id)
        .then((response) => {
          this.userMessage = response.data;
        });
    },
    formatDate(date){
      moment.locale("fr");
      return moment(date).calendar();
    },
    addMessage(data) {
      axios
        .post("/api/messages/add", {
          text: this.content,
          salonId: this.salon.id,
          userId: 7,
        })
        .then((response) => {
          this.fetchMessages();
        });
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
.date{
  color:white;
  font-size: small;
}
.text{
  color:white;
  font-size: large;
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