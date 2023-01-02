import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [  //здесь необходимо добавить пути до создаваемых вкладок
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/listing',
    name: 'listing',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "listing" */ '../views/ListingView.vue')
  },
  {
    path: '/insert',
    name: 'insert',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "insert" */ '../views/InsertView.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
