package com.heima.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
//lombok  在编译阶段，为实体类自动生成setter getter toString
//1.在pom文件上引入依赖 2.在实体类上添加注解
@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore  //让springmvc把当前对象转换成json字符串的时候，忽略password，最终的json字符串中就没有password这个属性了
//    com.fasterxml.jackson.annotation.JsonIgnore 注意：要导入这个类，上次搞混了导致没有作用
    private String password;//密码


    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称

    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}