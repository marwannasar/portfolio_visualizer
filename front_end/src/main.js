import Vue from 'vue'
import App from './App.vue'
import "vuetify/dist/vuetify.min.css"
import Vuetify from 'vuetify'
import axios from 'axios'
import VueAxios from 'vue-axios'

window.axios = require('axios');


Vue.config.productionTip = false
Vue.use(Vuetify)
Vue.use(VueAxios,axios)

new Vue({
  vuetify:new Vuetify(),
  render: h => h(App),
  mounted: function(){
  }
}).$mount('#app')