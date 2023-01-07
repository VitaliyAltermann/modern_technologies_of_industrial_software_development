import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [  //здесь необходимо добавить пути до создаваемых вкладок
  {
    path: '/',
    name: 'listingWaybill',
    component: () => import(/* webpackChunkName: "listing" */ '../views/1ListingWaybillView.vue'),
    
    meta: {   //роли, с которыми можно просматривать страницу
      rolesAllowed: 'readWaybill' 
    } 
  },
  {
    path: '/createWaybill',
    name: 'createWaybill',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "createWaybill" */ '../views/2CreateWaybillView.vue'),
    
    meta: {   //роли, с которыми можно просматривать страницу
      rolesAllowed: 'editWaybill' 
    } 
  },
  {
    path: '/createEmployee',
    name: 'createEmployee',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "createEmployee" */ '../views/3CreateEmployeeView.vue'),
    
    meta: {   //роли, с которыми можно просматривать страницу
      rolesAllowed: 'editEmployee' 
    } 
  },
  {
    path: '/createContract',
    name: 'createContract',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "createContract" */ '../views/4CreateContractView.vue'),
    
    meta: {   //роли, с которыми можно просматривать страницу
      rolesAllowed: 'editContract' 
    } 
  },
  {
    path: '/createCargo',
    name: 'createCargo',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "createCargo" */ '../views/5CreateCargoView.vue'),
    
    meta: {   //роли, с которыми можно просматривать страницу
      rolesAllowed: 'editCargo' 
    } 
  }
]

const router = new VueRouter({
  routes
})

//защита страниц от доступа без авторизации
router .beforeEach((to, from, next) => {  //перед каждым переходом
  
  // Получаем актуальный URL-адрес приложения, он нужен для Keycloak
  const basePath = window .location.toString()
  if (! Vue.$keycloak.authenticated) {
    // Страница защищена, а пользователь не аутентифицирован. Принудительный вход в систему.
    Vue.$keycloak.login({ redirectUri: basePath.slice(0, -1) + to.path })
  } else if ((Vue.$keycloak.hasResourceRole(to.meta.rolesAllowed))||(to ==null)) {
    // Пользователь прошел аутентификацию и имеет подходящую роль
    Vue.$keycloak.updateToken()
      .then(() => {
        next()
      })
      .catch(err => { console
        .error(err) 
      }) 
  } else { 
    // Пользователь прошел аутентификацию, но не имеет правильной роли 
    // оставление на странице
    alert("У Вас нет права доступа на страницу " + to.name)
    next({ name: ('listingWaybill') }) 
  } 
  
})

export default router
