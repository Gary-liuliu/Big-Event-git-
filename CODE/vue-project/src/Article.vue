<script setup>
import { articleGetAllService, articleSearchService } from "@/api/article.js";
// 导入Axios,要先在该项目的终端 下安装axios,npm install axios

// import axios from "axios";
// 响应式数据导入
import { ref } from "vue";

// 创建响应式数组
const articleList = ref([]);

// 同步获取返回结果 async await
const getAllArticle = async function () {
  let data = await articleGetAllService();
  articleList.value = data;
};

getAllArticle();

// 创建响应式数据
const searchConditions = ref({
  category: "",
  state: "",
});

const search = async function () {
  let date = await articleSearchService({ ...searchConditions.value });
  articleList.value = date;
};

// axios
//   .get("http://localhost:8080/article/getAll")
//   .then((res) => {
//     articleList.value = res.data;
//   })
//   .catch((err) => {
//     console.error(err);
//   });

// const search = function () {
//   axios
//     .get("http://localhost:8080/article/search", {
//       params: { ...searchConditions.value },
//     })
//     .then((res) => {
//       articleList.value = res.data;
//     })
//     .catch((err) => {
//       console.error(err);
//     });
// };
</script>

<template>
  <div>
    文章分类: <input type="text" v-model="searchConditions.category" />

    发布状态: <input type="text" v-model="searchConditions.state" />

    <button v-on:click="search">搜索</button>

    <br />
    <br />
    <table border="1 solid" colspa="0" cellspacing="0">
      <tr>
        <th>文章标题</th>
        <th>分类</th>
        <th>发表时间</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      <tr v-for="(article, index) in articleList">
        <td>{{ article.title }}</td>
        <td>{{ article.category }}</td>
        <td>{{ article.time }}</td>
        <td>{{ article.state }}</td>
        <td>
          <button>编辑</button>
          <button>删除</button>
        </td>
      </tr>
    </table>
  </div>
</template>