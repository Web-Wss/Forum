// 该文件用于创建Vuex中最核心的store
import Vue from "vue";
import Vuex from "vuex";
import storage from "../utils/storage";
Vue.use(Vuex);

// 用vuex.Store对象来记录token
const store = new Vuex.Store({
  state: {
    token: "",
  },

  getters: {
    getToken(state) {
      return state.token || storage.get("token") || "";
    },
  },

  mutations: {
    // 修改token，并将token存入localstorage
    setToken(state, token) {
      state.token = token;
      storage.set("token", token);
      console.log("store、localstorage保存token成功");
    },
    delToken(state) {
      state.token = "";
      storage.remove("token");
    },
  },

  actions: {},
});

export default store;
