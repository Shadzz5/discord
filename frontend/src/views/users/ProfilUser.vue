<template>
  <div class="profil">
    <h3>{{ user.username }}</h3>
    <i class="icon" data-feather="cirlce"></i>
  </div>

</template>
<script>
export default {
  components: {
  },
  data() {
    return {
      user: {},
      status:[{id:1,name:"online", label:"Disponible"},{id:2,name:"offline",label:"Déconnecté"},{id:3,name:"invisible", label:"Invisible"}]
    };
  },
  created() {
    this.user = JSON.parse(localStorage.getItem("user"));
  },
  // mounted() {
  // }
};
</script>
<style scoped>
.icon{
    background-color: white;
    color: white;
}
.profil {
  color: white;
  background-color: #1e1f22;
  border-radius: 10px;
}
</style>