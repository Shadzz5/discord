import './assets/main.css'
import axios from 'axios';

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueFeather from 'vue-feather';
const app = createApp(App)
axios.defaults.baseURL = 'http://localhost:8080'; // Remplacez par l'URL de votre API

app.use(router)
app.component(VueFeather.name, VueFeather);
app.mount('#app')
