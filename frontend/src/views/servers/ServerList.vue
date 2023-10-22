<!-- MenuComponent.vue -->
<template>
  <div class="container">
    <div class="main-container">
      <button @click="showAddServerModal = true">Ajouter un serveur</button>
        <div
          class="button-menu"
          v-for="server in servers"
          :key="server.id"
          @click="showServerDetails(server)"
        >
          {{ server.name }}
        </div>
      <div class="profile">
        <ProfilUser />
      </div>
    </div>
    <div class="server-details-container">
      <ServerDetails
        :server="selectedServerId"
        v-if="selectedServerId !== null"
      />
      <Modal
        :showModal="showAddServerModal"
        @close="showAddServerModal = false"
      >
        <h2>Ajouter un serveur</h2>
        <label>Ajouter un nom </label>
        <input type="text" v-model="newServerName" />
        <button @click="addServer">Ajouter un serveur</button>
      </Modal>
    </div>
  </div>
</template>
  
  <script>
import axios from "axios";
import ServerDetails from "./ServerDetails.vue";
import Modal from "@/components/Modal.vue"; // Assurez-vous de spécifier le bon chemin d'importation
import ProfilUser from "../users/ProfilUser.vue";

export default {
  components: {
    ServerDetails,
    Modal,
    ProfilUser,
  },
  data() {
    return {
      servers: [], // Vos données de serveur ici
      selectedServerId: null,
      newServerName: "",
      showAddServerModal: false,
    };
  },
  mounted() {
    this.fetchServers();
  },
  created() {
    this.fetchServers();
  },

  methods: {
    addServer() {
      axios
        .post("/api/servers/add", {
          name: this.newServerName,
        })
        .then((response) => {
          (this.showAddServerModal = false), this.fetchServers();
        })
        .catch(() => {});
    },
    fetchServers() {
      axios
        .get("/api/servers/all") // Remplacez par l'URL de votre endpoint
        .then((response) => {
          this.servers = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    showServerDetails(server) {
      console.log(server);
      // Mettez à jour selectedServerId avec l'ID du serveur sélectionné
      this.selectedServerId = server;
    },
  },
};
</script>
  
  <style scoped>
.container {
  display: flex;
}
.main-container {
  height: 55rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* width: 50%; */
  background-color: #3d4148;
  border-radius: 10px;
}
.button-menu {
  cursor: pointer;
  text-align: center;
  font-size: 12px;
  color: white;
}
/* nav {
  text-align: center;
  font-size: 12px;
  margin-top: 1rem;
  color: white;
} */
.server-details-container {
  justify-content: space-between;
  flex-direction: row;
  padding-left: 1rem; /* Ajoutez de l'espace à gauche pour séparer le menu du détail du serveur */
  width: 100%;
}
/* Vos autres styles ici */
</style>
  