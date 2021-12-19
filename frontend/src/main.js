import Vue from 'vue'
import App from './App.vue'
import router from './routes.js'
import VCalendar from 'v-calendar';

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

// Use v-calendar & v-date-picker components
Vue.use(VCalendar, {
  locales: {
    
  }

});
