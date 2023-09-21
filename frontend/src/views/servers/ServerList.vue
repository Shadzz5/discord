<!-- MenuComponent.vue -->
<template>
    <div>
      <div class="main-container">
        <nav>
          <div class="button-menu"
            v-for="server in servers"
            :key="server.id"
            @click="showServerDetails(server.id)"
          >
            {{ server.name }}
          </div>
        </nav>
        <div class="server-details-container">
          <ServerDetails
            :serverId="selectedServerId"
            v-if="selectedServerId !== null"
          />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import ServerDetails from "./ServerDetails.vue";
  
  export default {
    components: {
      ServerDetails,
    },
    data() {
      return {
        servers: [{ id: 1, name: "Serveur 1 " },{ id: 2, name: "Serveur 2 " },{ id: 3, name: "Serveur 3" }], // Vos données de serveur ici
        selectedServerId: null,
      };
    },
    methods: {
      showServerDetails(serverId) {
        // Mettez à jour selectedServerId avec l'ID du serveur sélectionné
        this.selectedServerId = serverId;
      },
    },
  };
  </script>
  
  <style scoped>
  .main-container {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
  }
  .button-menu{
    cursor: pointer;
  }
  nav {
    text-align: center;
    font-size: 12px;
    margin-top: 1rem;
    color:white;
  }
  
  .server-details-container {
    flex-grow: 1; /* Faites en sorte que le contenu à droite prenne tout l'espace disponible */
    padding-left: 1rem; /* Ajoutez de l'espace à gauche pour séparer le menu du détail du serveur */
  }
  /* Vos autres styles ici */
  </style>
  