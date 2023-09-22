<script>
import ServerList from "./servers/ServerList.vue";
export default {
  components: {
    ServerList,
  },
  methods:{
    logout(){
      localStorage.removeItem("token");
      this.$router.push("/login");
    }
  }
};
</script>

<template>
    <div class="border">
      <p>Discord</p>
      <button @click="logout">Déconnecter</button>
    </div>
  <main>
    <ServerList />
  </main>
</template>
<style scoped>
.border {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  color:white;
  border-bottom:4px solid #26282b;
  width: 100%; /* Ajoutez cette ligne pour étendre l'élément sur toute la largeur */
}
</style>