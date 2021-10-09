import Vue from 'vue'
import App from './App.vue'
import "vuetify/dist/vuetify.min.css"
import Vuetify from 'vuetify'
import axios from 'axios'
import VueAxios from 'vue-axios'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import VueApexCharts from 'vue-apexcharts'

window.axios = require('axios');

Vue.prototype.$totalDollarPL

Vue.config.productionTip = false
Vue.use(Vuetify)
Vue.use(VueAxios,axios)
Vue.use(VueApexCharts)
Vue.component('apexchart', VueApexCharts);

new Vue({
  vuetify:new Vuetify(),
  render: h => h(App),
  mounted: function(){}

}).$mount('#app') 

