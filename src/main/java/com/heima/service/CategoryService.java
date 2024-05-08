package com.heima.service;

import com.heima.pojo.Category;

import java.util.List;

public interface CategoryService {
//    新增分类
    void add(Category category);

//    列表查询
    List<Category> list();

//    根据Id查询分类
    Category findById(Integer id);

//    修改分类
    void update(Category category);

    //删除分类
    void delete(Integer id);
}