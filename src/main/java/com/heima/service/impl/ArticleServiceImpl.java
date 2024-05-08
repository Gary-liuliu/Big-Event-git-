package com.heima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.mapper.ArticleMapper;
import com.heima.pojo.Article;
import com.heima.pojo.PageBean;
import com.heima.service.ArticleService;
import com.heima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {

        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
//        创建PageBeaan对象
        PageBean<Article> pb  = new PageBean<>();

//        开启分页查询PageHelper
        PageHelper.startPage(pageNum,pageSize);

//        调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as  = articleMapper.list(userId,categoryId, state);

//        Page中提供了方法可以获取PageHelper分页查询后得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;

//        把数据填充进PageBean对象中去
        pb.setItems(p.getResult());
        pb.setTotal(p.getTotal());
        return  pb;
    }

    @Override
    public Article getArticle(Integer id) {
        Article article = articleMapper.getArticle(id);
        return article;
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

}