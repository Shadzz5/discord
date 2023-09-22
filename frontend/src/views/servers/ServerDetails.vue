<!-- ServerDetails.vue -->

<template>
  <h2>{{ server.name }}</h2>
  <button @click="showAddSalonModal = true">Ajouter un salon</button>
  <div class="containerBis" v-if="server != null">
    <div class="salonList">
      <div
        v-for="salon in salons"
        :key="salon.id"
        class="button-menu listSalon"
        @click="showSalonDetails(salon)"
      >
        {{ salon.name }}
      </div>
    </div>
    <div
      class="main-container"
      v-if="selectedSalonId != null && selectedSalonId.isText == true"
    >
      <ChatView :salon="selectedSalonId" />
    </div>
    <div class="userList"><UserList /></div>
  </div>
  <Modal :showModal="showAddSalonModal" @close="showAddSalonModal = false">
    <h2>Ajouter un salon</h2>
    <label>Ajouter un nom </label>
    <input type="text" v-model="newSalonName" />
    <label>Texte </label>
    <input type="radio" v-model="isText" value="true" />
    <label>Vocal </label>
    <input type="radio" v-model="IsText" value="false" />
    <button @click="addSalon">Ajouter un salon</button>
  </Modal>
</template>
  
  <script>
import axios from "axios";
import ChatView from "../chat/ChatView.vue";
import UserList from "../users/UserList.vue";
import Modal from "../../components/Modal.vue";

export default {
  components: {
    ChatView,
    UserList,
    Modal,
  },
  props: ["server"],
  data() {
    return {
      salons: [],
      selectedSalonId: null,
      showAddSalonModal: false,
      newSalonName: null,
      isText: true,
    };
  },
  watch: {
    server: {
      handler: function () {
        this.fetchSalons();
        this.selectedSalonId = null;
      },
      immediate: true,
    },
  },
  mounted() {
    this.fetchSalons();
  },
  methods: {
    addSalon() {
      axios
        .post("/api/salons/add", {
          name: this.newSalonName,
          isText: this.isText,
          serverId: this.server.id,
        })
        .then((response) => {
          console.log(response);
          this.fetchSalons();
          this.showAddSalonModal = false;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    fetchSalons() {
      axios
        .get("/api/salons/allSalons?serverId=" + this.server.id)
        .then((response) => {
          this.salons = response.data;
        })
        .catch((error) => error);
    },
    showSalonDetails(salon) {
      console.log(salon);
      this.selectedSalonId = salon;
    },
  },
};
</script>
<style>
.containerBis {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100% ;
}
h1 {
  color: white;
}
h2 {
  color: white;
}
/* .main-container {
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
} */
/* 
.userList {
  display: flex;
  flex-direction: row;
  padding-left: 1rem;
}
.userList {
  display: flex;
  flex-direction: column;
  padding-left: 1rem; 
} */
.button-menu {
  cursor: pointer;
  color: white;
}
nav {
  text-align: center;
  font-size: 12px;
  margin-top: 1rem;
  color: white;
}
/* Vos autres styles ici */
</style>
  