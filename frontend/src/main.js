import './assets/main.css'
import axios from 'axios';

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueFeather from 'vue-feather';
const app = createApp(App)
axios.defaults.baseURL = 'http://localhost:8080'; // Remplacez par l'URL de votre API
const token = localStorage.getItem('token');
console.log(token);
if(token != ""){
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.defaults.headers.post['Content-Type'] = 'application/json';
}

app.use(router)
app.component(VueFeather.name, VueFeather);
app.mount('#app')
