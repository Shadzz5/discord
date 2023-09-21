<!-- ServerDetails.vue -->

<template>
  <div class="main-container">
    <h1>{{ serverId }}</h1>
    <div>
      <ChatView />
    </div>
    <UserList />
  </div>
</template>
  
  <script>
import ChatView from "../chat/ChatView.vue";
import UserList from "../users/UserList.vue";

export default {
  components: {
    ChatView,
    UserList,
  },
  props: ["serverId"],
};
</script>
  
  <style>
h1 {
  color: white;
}
.main-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
.button-menu {
  cursor: pointer;
}
nav {
  text-align: center;
  font-size: 12px;
  margin-top: 1rem;
  color: white;
}
/* Vos autres styles ici */
</style>
  