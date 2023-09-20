<template>
  <div>
    <h1>Serveur</h1>
    <Menu :servers="servers" />
  </div>
</template>
<script>
import Menu from "../../components/menu/Menu.vue";
export default {
    components(){
        Menu
    },
  data() {
    return {
      servers: [{ id: "id", name: "name" }],
    };
  },
  setup() {},
  created(){
  }
};
</script>
