import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue} from 'bootstrap-vue'

// Импортируйте файлы CSS Bootstrap и BootstrapVue (порядок важен)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'   //позволяет осуществялть запросы
import VueAxios from 'vue-axios'

import '@/plugins/keycloak'
import { updateToken } from '@/plugins/keycloak-util'


// Сделайте BootstrapVue доступным для всего проекта
Vue.use(BootstrapVue, axios, VueAxios)

Vue.config.productionTip = false

//аутентификация и загрузка

Vue.$keycloak.init({ onLoad: 'login-required' }).then((auth) => {
  if (!auth) {
    window.location.reload();
  } else {
    //Vue.$log.info("Authenticated");

    new Vue({
      router,
      el: '#app',
      render: h => h(App, )
    }).$mount('#app')

    
    window.onfocus = () => {  //обновление токена при переходе на вкладку
      updateToken()
    }

  }


  /*
  localStorage.setItem("vue-token",keycloak.token);
  localStorage.setItem("vue-refresh-token",keycloak.refreshToken)
  */

//Token Refresh

/*
  setInterval(() => {
    keycloak.updateToken(70).then((refreshed) => {
      if (refreshed) {
        //Vue.$log.info('Token refreshed' + refreshed);
      } else {
        //Vue.$log.warn('Token not refreshed, valid for '
         // + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
      }
    }).catch(() => {
      //Vue.$log.error('Failed to refresh token');
    });
  }, 6000)
  */

}).catch(() => {
  //Vue.$log.error("Authenticated Failed");
});


/*
new Vue({
  router,
  render: h => h(App),

  
}).$mount('#app')
*/

