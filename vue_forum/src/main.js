import Vue from "vue";
import App from "./App.vue";

// 引入图标库
import "../src/assets/css/iconfont/iconfont.css";
import "../src/assets/css/iconfont/iconfont";

// 引入vue-router
import VueRouter from "vue-router";
Vue.use(VueRouter);

// 引入Vant
import Vant from "vant";
import "vant/lib/index.css";
Vue.use(Vant);

import router from "./router";
import store from "./store/index";

// 引入http
import http from "./utils/request";
Vue.prototype.$http = http;

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");
