import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import vueCookies from "vue-cookies";

Vue.config.productionTip = false;
Vue.use(vueCookies);

//cookies 만료시간 일단 12시간으로 설정
Vue.$cookies.config(60 * 60 * 12);

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");

window.Kakao.init("26ee7d8e0d156c4f3b4b85e30fb93cef");
