package com.heima.controller;

import com.heima.pojo.Article;
import com.heima.pojo.PageBean;
import com.heima.pojo.Result;
import com.heima.service.ArticleService;
import com.heima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.description.field.FieldDescription;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    @GetMapping("/getArticle")
    public Result<Article> getArticle(Integer id){
            Article article = articleService.getArticle(id);
            return Result.success(article);
    }

    @DeleteMapping
    public Result deleteArticle(Integer id){
        articleService.deleteArticle(id);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state

    ){
        PageBean<Article> pb =  articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
}