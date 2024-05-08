import { createRouter, createWebHistory } from "vue-router";
//npm install ...要先下载
//导入组件
import LoginVue from "@/views/Login.vue";
import Layout from "@/views/Layout.vue";
import ArticleCategoryVue from "@/views/article/ArticleCategory.vue";
import ArticleManageVue from "@/views/article/ArticleManage.vue";
import UserAvatarVue from "@/views/user/UserAvatar.vue";
import UserInfoVue from "@/views/user/UserInfo.vue";
import UserResetPassword from "@/views/user/UserResetPassword.vue";

//定义路由关系
const routes = [
  {
    path: "/login",
    component: LoginVue,
  },
  {
    path: "/",
    component: Layout,
    redirect: "/article/manage", //重定向，访问“/”时默认跳转"/article/manage"
    //配置子路由
    children: [
      { path: "/article/category", component: ArticleCategoryVue },
      { path: "/article/manage", component: ArticleManageVue },
      { path: "/user/avatar", component: UserAvatarVue },
      { path: "/user/info", component: UserInfoVue },
      { path: "/user/resetPassword", component: UserResetPassword },
    ], //在Layout文件展示区域里导入<router-view>，在点击的el-menu-item里加入路径index="/article/category"
  },
];

//创建路由器

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

//导出路由
export default router;
