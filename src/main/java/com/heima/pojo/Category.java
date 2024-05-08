package com.heima.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

//注意要分组校验，在add时id不需要notnull，而在update时id需要notnull

@Data
public class Category {

//    不能不传
//    2.指定分组
    @NotNull(groups = Update.class)
    private Integer id;//主键ID

//    不能不传且不能为空
    @NotEmpty(groups = {Add.class,update.class})
    private String categoryName;//分类名称

    @NotEmpty(groups = {Add.class,update.class})
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

//如果没有指定分组，默认Default
//如果A继承B，那么B的所有校验项A也会拥有
//    1.定义分组
    public interface Add{

    }
    public interface update{

    }
}