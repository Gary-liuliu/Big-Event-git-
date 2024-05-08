import { createApp } from "vue";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import App from "./App.vue";
import router from "@/router"; //默认找到该文件夹下的index文件
import { createPinia } from "pinia";
import { createPersistedState } from "pinia-persistedstate-plugin"; //token持久化保存，防止刷新消失，先下载npm install pinia-persistedstate-plugin
import locale from "element-plus/dist/locale/zh-cn.js";

const app = createApp(App);

const pinia = createPinia();

const persist = createPersistedState();

pinia.use(persist);
app.use(pinia);
app.use(router);
app.use(ElementPlus, { locale }); //注意不要少括号
app.mount("#app");
