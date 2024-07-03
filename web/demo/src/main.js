import Vue from "vue";
import App from "./App.vue";
import VueRouter from "vue-router";
import router from "./router/index";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import uploader from "vue-simple-uploader";
Vue.use(uploader);
Vue.use(ElementUI);
Vue.use(VueRouter);
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

Vue.prototype.$axios = axios;

Vue.config.productionTip = false;
new Vue({
  render: (h) => h(App),
  router,
}).$mount("#app");
