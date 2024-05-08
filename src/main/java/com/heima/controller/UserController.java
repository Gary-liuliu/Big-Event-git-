package com.heima.controller;

import com.heima.pojo.Result;
import com.heima.pojo.User;
import com.heima.service.UserService;
import com.heima.utils.JwtUtil;
import com.heima.utils.Md5Util;
import com.heima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//参数校验注解
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
//    Pattern参数校验，最少6位最大16位
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{10,16}$")String password){
//        查询用户
        User u = userService.findByUserName(username);
        if(u == null){
//              没有占用
//            注册
            userService.register(username,password);
            return Result.success();
        }else{
//            占用
            return Result.error("用户名已经被占用了");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{10,16}$")String password){
//    根据用户名查询用户
        User loginUser =userService.findByUserName(username);
//        判断用户是否存在
        if(loginUser == null){
            return Result.error("用户名错误");
        }
//        判断密码是否正确 注：数据库是密文
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){

            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        System.out.println(loginUser.getPassword());
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*改用ThreadLocal*//*@RequestHeader(name = "Authorization") String token*/){
//        从请求头上获取令牌，再反编译出该用户的用户名
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");


//        调用ThreadLocal方法，比较方便
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
//        找到该对象
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
//    @Validated实体参数校验，还有User类中的@NotNull，@NotEmpty,@Email一起使用
    public Result update(@RequestBody @Validated User user){
        userService.update(user);

        return Result.success();
    }

//    @URL保证进入的是一个合法的URL
//    更新一个字段的信息，所以用Patch请求
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){

//        校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");

        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);

//        校验原密码
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码不正确");
        }

//        校验新密码
        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的密码不正确");
        }

        userService.updatePwd(newPwd);
        return Result.success();
    }
}