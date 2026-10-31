import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/Dashboard',
      name: 'Dashboard',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },

    {
      path: '/Stocklist',
      name: 'StockList',
      component: () => import('../views/StockList.vue'),
    },
    {
      path: '/Fokus',
      name: 'Fokus',
      component: () => import('../views/Fokus.vue'),
    }
  ],
})

export default router
