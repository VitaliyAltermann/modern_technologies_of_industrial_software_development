import Vue from 'vue'
import Keycloak from 'keycloak-js'
//import KeycloakService from 'keycloak-js'

const initOptions = {
    url: 'http://127.0.0.1:8180/', realm: 'coursework', clientId: 'my-work', onLoad: 'login-required'
  }

const keycloak = new Keycloak(initOptions)
//const keycloakService=new KeycloakService()

const KeycloakPlugin = {
    install: Vue => {
        Vue.$keycloak = keycloak
        
    }
}

Vue.use(KeycloakPlugin)

export default KeycloakPlugin