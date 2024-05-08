import request from "@/utils/request.js";
import { useTokenStore } from "@/stores/token.js";

//文章分类列表查询
export const ArticleCategoryListService = () => {
  const tokenStore = useTokenStore();
  //在pinia中定义的响应式 数据都不需要.value
  // return request.get("/category", {
  //   headers: { Authorization: tokenStore.token },
  // });
  return request.get("/category");
};
//文章添加分类接口
export const articleCategoryAddService = (categoryData) => {
  return request.post("/category", categoryData);
};

//修改文章分类
export const articleCategoryUpdateService = (categoryData) => {
  return request.put("/category", categoryData);
};

//文章分类删除
export const articleCategoryDeleteService = (id) => {
  return request.delete(`/category?id=` + id);
};

//文章列表查询
export const articleListService = (params) => {
  return request.get("/article", { params: params });
};

//文章添加
export const articleAddService = (articleData) => {
  return request.post("/article", articleData);
};

//文章删除
export const articleDeleteService = (id) => {
  return request.delete(`/article?id=` + id);
};

//修改文章
export const articleUpdateService = (articleData) => {
  return request.put("/article", articleData);
};
