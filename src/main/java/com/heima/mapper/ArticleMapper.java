package com.heima.mapper;

import com.heima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

//动态sql
    List<Article> list(Integer userId, Integer categoryId, String state);

    //查找文章
    @Select("select * from article where id = #{id}")
    Article getArticle(Integer id);

    //删除文章
    @Delete("delete from article where id = #{id}")
    void deleteArticle(Integer id);

    //修改文章
    @Update("update article set title=#{title},content = #{content},category_id =#{categoryId},cover_img=#{coverImg},update_time=#{updateTime},state=#{state} " +
            "where id=#{id}")
    void update(Article article);
}