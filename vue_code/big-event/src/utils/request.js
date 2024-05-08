//定制请求的实例

//导入axios  npm install axios
import axios from "axios";
//导入element-plus消息提示框
import { ElMessage } from "element-plus";
//定义一个变量,记录公共的前缀  ,  baseURL
// const baseURL = 'http://localhost:8080'; //会出现跨域问题
const baseURL = "/api";
const instance = axios.create({ baseURL });

import { useTokenStore } from "@/stores/token.js";
//添加请求拦截器
instance.interceptors.request.use(
  (config) => {
    //请求前的回调
    //添加token
    const tokenStore = useTokenStore();
    if (tokenStore.token) {
      config.headers.Authorization = tokenStore.token;
    }
    return config;
  },
  (err) => {
    //请求错误的回调
    Promise.reject(err);
  }
);

// import { useRoute } from "vue-router";
// const router = useRoute(); 无效

import router from "@/router";
//添加响应拦截器
instance.interceptors.response.use(
  (result) => {
    //注册和登录统一判断业务
    if (result.data.code === 0) {
      return result.data;
    }
    // alert(result.data.message ? result.data.message : "服务异常");
    ElMessage.error(result.data.message ? result.data.message : "服务异常");
    // 异步操作的状态转换为失败
    return Promise.reject(result.data);
  },
  (err) => {
    //判断响应转态码，如果为401，则未登录，那么要跳转到登录页面
    if (err.response.status === 401) {
      ElMessage.error("未登录");
      router.push("/login");
    } else {
      alert("服务异常");
    }
    return Promise.reject(err); //异步的状态转化成失败的状态
  }
);

export default instance;
