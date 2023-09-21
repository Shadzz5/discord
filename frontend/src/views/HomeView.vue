<script>
import ServerList from "./servers/ServerList.vue";
export default {
  components: {
    ServerList,
  },
};
</script>

<template>
    <div class="border">
      <p>Discord</p>
    </div>
  <main>
    <ServerList />
  </main>
</template>
<style scoped>
.border {
  color:white;
  border-bottom:4px solid #26282b;
  width: 100%; /* Ajoutez cette ligne pour étendre l'élément sur toute la largeur */
}
</style>