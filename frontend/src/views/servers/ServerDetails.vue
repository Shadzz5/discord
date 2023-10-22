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
    <div v-if="selectedSalonId != null">
      <div v-if="selectedSalonId.isText == true">
        <ChatView :salon="selectedSalonId" />
      </div>
      <div v-else>
        <div>
          <button @click="startScreenSharing">Partagé l'écran</button>
          <button @click="startCamera">Rejoindre le salon</button>
          <button @click="activeCamera">Activer la caméra</button>
          <button @click="stopMedia">Quittez le salon</button>
          <button @click="downloadVideo">Download Video</button>
          <button @click="toggleMute">Mute</button>
        </div>
        <div>
          <button @click="startRecording" :disabled="recording">
            Start Recording
          </button>
          <button @click="stopRecording" :disabled="!recording">
            Stop Recording
          </button>
        </div>
        <video ref="localVideo" autoplay :muted="mute">Live</video>
      </div>
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
import { saveAs } from "file-saver";
import io from "socket.io-client";

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
      localStream: null,
      mediaRecorder: null,
      recording: false,
      video: null,
      mute: false,
      active: false,
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
    toggleMute() {
      this.mute = !this.mute;
    },
    activeCamera() {
      this.active = !this.active;
      this.startCamera();
    },
    startRecording() {
      if (this.mediaRecorder) {
        this.mediaRecorder.start();
        this.recording = true;
      }
    },
    stopRecording() {
      if (this.mediaRecorder && this.mediaRecorder.state === "recording") {
        this.mediaRecorder.stop();
        this.recording = false;
      }
    },
    handleDataAvailable(event) {
      if (event.data.size > 0) {
        const blob = new Blob([event.data], { type: "video/mp4" });
        const videoUrl = URL.createObjectURL(blob);
        const formData = new FormData();
        formData.append("video", blob, "video.mp4");
        const config = {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        };

        axios
          .post("/api/video/upload-video", formData, config)
          .then((response) => console.log(response))
          .catch((error) => console.error(error));
      }
    },
    async startScreenSharing() {
      try {
        const stream = await navigator.mediaDevices.getDisplayMedia({
          video: true,
        });
        this.localStream = stream;
        this.$refs.localVideo.srcObject = stream;
      } catch (error) {
        console.error("Error sharing screen:", error);
      }
    },
    async startCamera() {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: this.active,
          audio: true,
        });
        this.localStream = stream;
        this.$refs.localVideo.srcObject = stream;
        this.mediaRecorder = new MediaRecorder(stream);
        this.mediaRecorder.ondataavailable = this.handleDataAvailable;
        this.mediaRecorder.onstop = this.handleStop;
      } catch (error) {
        console.error("Error accessing camera:", error);
      }
    },
    stopMedia() {
      if (this.localStream) {
        this.localStream.getTracks().forEach((track) => {
          track.stop();
        });
        this.$refs.localVideo.srcObject = null;
        this.localStream = null;
      }
    },
    addSalon() {
      axios
        .post("/api/salons/add", {
          name: this.newSalonName,
          isText: this.isText,
          serverId: this.server.id,
        })
        .then((response) => {
          this.fetchSalons();
          this.showAddSalonModal = false;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    downloadVideo() {
      const formData = new FormData();
      formData.append("video", "video.mp4");
      const config = {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      };
      axios
        .get("/api/video/download-video?video=" + "video.mp4", {
          responseType: "blob",
        })
        .then((response) => {
          saveAs(new Blob([response.data]), "video.mp4");
        });
    },
    fetchSalons() {
      // const socket = io("http://localhost:8080/screening"); // Remplacez par l'URL de votre backend WebSocket
      // console.log(socket);
      axios
        .get("/api/salons/allSalons?serverId=" + this.server.id)
        .then((response) => {
          this.salons = response.data;
        })
        .catch((error) => error);
    },
    showSalonDetails(salon) {
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
  width: 100%;
}
h1 {
  color: white;
}
h2 {
  color: white;
}

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
  