import axios from "axios";
import store from "../store";
import router from "../router";

const service = axios.create({
  // 设置了代理（解决跨域）
  baseURL: "/api",
  timeout: 5000,
});

// 添加请求拦截器，若token存在则在请求头中加token，不存在也继续请求
service.interceptors.request.use(
  (config) => {
    // 每次发送请求之前都检测vuex是否存有token，放在请求头发送给服务器
    // Authorization是根据后端自定义字段
    config.headers.Authorization = store.getters.getToken;
    return config;
  },
  (error) => {
    console.log("在request拦截器显示错误", error.response);
    return Promise.reject(error);
  }
);

// response拦截器
service.interceptors.response.use(
  (response) => {
    return response.data.code == 200
      ? response
      : Promise.reject(response.data.message);
  },
  (error) => {
    if (error.response) {
      // console.log("在response拦截器显示错误", error.response);
      switch (error.response.status) {
        case 500:
          // 禁止访问
          // 清除token
          store.commit("delToken");
          router.replace({
            // 跳转到登录页面
            path: "/login",
            query: { redirect: router.currentRoute.fullPath },
          });

        // router
        //   .push({
        //     name: "login",
        //     // query: { redirect: router.currentRoute.fullPath },
        //   })
        //   .catch(() => {});
      }
    }
    return Promise.reject(error.response.data);
  }
);

export default service;
