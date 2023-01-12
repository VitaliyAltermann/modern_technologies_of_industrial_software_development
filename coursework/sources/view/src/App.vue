<template>
  <div id="app">
    <b-row class="mx-auto" style="width: 100%">

      <b-col cols="12">
        <h2>Курсовая работа по дисциплине "Современные технологии промышленной разработки ПО" Беляев А., Перхуров В. гр. ИВМ-22</h2>
      </b-col>

    </b-row>

    <b-row class="mx-auto" style="width: 100%">
      <b-col cols="1"></b-col>
      <b-col cols="10">
        <nav>   
          <!-- указываются сслки на страницы и их названия -->
          <router-link to="/" v-if="accesListingWaybill">Просмотр накладных</router-link> <label v-if="accesListingWaybill"> | </label> 
          <router-link to="/createWaybill" v-if="accesCreateWaybill">Создание накладной</router-link> <label v-if="accesCreateWaybill"> | </label> 
          <router-link to="/createEmployee" v-if="accesCreateEmployee">Добавление сотрудника</router-link> <label v-if="accesCreateEmployee"> | </label> 
          <router-link to="/createContract" v-if="accesCreateContract">Создание договора</router-link> <label v-if="accesCreateContract"> | </label> 
         <router-link to="/createCargo" v-if="accesCreateCargo">Добавление имущества</router-link>
        </nav>
         
      </b-col>
      <b-col cols="1">
        <b-button  variant="outline-primary"  @click="logOut()">Выйти </b-button>
      </b-col>

    </b-row>
    <router-view/>
    
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

</style>


<script>
//авторозация перехватчики запросов и ответов

import Vue from 'vue'
import axios from 'axios'
import { updateToken } from '@/plugins/keycloak-util'


//const AUTHORIZATION_HEADER = 'Authorization'

export default Vue.extend({
  name: 'App',

  data(){
    return{

      //права доступа на страницы (отображение страниц)
      accesListingWaybill:Vue.$keycloak.hasResourceRole('readWaybill'),
      accesCreateEmployee:Vue.$keycloak.hasResourceRole('editEmployee'),
      accesCreateWaybill:Vue.$keycloak.hasResourceRole('editWaybill'),
      accesCreateContract:Vue.$keycloak.hasResourceRole('editContract'),
      accesCreateCargo:Vue.$keycloak.hasResourceRole('editCargo'),

    }
  },

  created: function () {
    axios.interceptors.request.use(async config => {
      // Обновляем токен
      const token =  await updateToken()
      //console.log(Vue.$keycloakService.getKeycloakInstance().tokenParsed['roles'])
      // Добавляем токен в каждый запрос
      config.headers.Authorization=`Bearer ${token}`
      
      return config
    })
    
    axios.interceptors.response.use( (response) => {
      return response
    }, error => {
      return new Promise((resolve, reject) => {
        // Если от API получена ошибка - отправляем на страницу /error
        //alert("Произошла ошибка при выполнении запроса " +error )
        reject(error)
      })
    })
  },
  


  

  methods:{
    logOut(){
      const basePath = window .location.toString()
      Vue.$keycloak.logout({ redirectUri: basePath.slice(0, -1) })
    },

    recomputeAcces(){
      //права доступа на страницы (отображение страниц)
      this.accesListingWaybill=Vue.$keycloak.hasResourceRole('readWaybill'),
      this.accesCreateEmployee=Vue.$keycloak.hasResourceRole('editEmployee'),
      this.accesCreateWaybill=Vue.$keycloak.hasResourceRole('editWaybill'),
      this.accesCreateContract=Vue.$keycloak.hasResourceRole('editContract'),
      this.accesCreateCargo=Vue.$keycloak.hasResourceRole('editCargo')
    }

  },

  // Обновляем токен при навигации
  watch: {
    $route() {
      updateToken(),
      this.recomputeAcces()  //и изменяем выдимоть вкладок
    }
  },

})

</script>