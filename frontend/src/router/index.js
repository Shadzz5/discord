import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/auth/Login.vue'
import RegisterView from '../views/auth/Register.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView
    }
  ]
})
router.beforeEach((to, from, next) => {
  // If the user is not logged in, redirect to /login
  const isLoggedIn = !!localStorage.getItem("token");
  if (to.name === "home" && !isLoggedIn) {
    return next({ path: "/login" });
  }

  next();
});
export default router
