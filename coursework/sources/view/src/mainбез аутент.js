import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue} from 'bootstrap-vue'

// Импортируйте файлы CSS Bootstrap и BootstrapVue (порядок важен)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'   //позволяет осуществялть запросы
import VueAxios from 'vue-axios'


// Сделайте BootstrapVue доступным для всего проекта
Vue.use(BootstrapVue, axios, VueAxios)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),

  
}).$mount('#app')


