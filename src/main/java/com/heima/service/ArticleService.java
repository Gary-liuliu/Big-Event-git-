package com.heima.service;

import com.heima.pojo.Article;
import com.heima.pojo.PageBean;

public interface ArticleService {
//    新增文章
    void add(Article article);

//    条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //查找文章
    Article getArticle(Integer id);

    //删除文章
    void deleteArticle(Integer id);

    //修改文章
    void update(Article article);
}