<script setup>
import { User, Lock } from "@element-plus/icons-vue";
import { ref } from "vue";
//导入element-plus消息提示框
import { ElMessage } from "element-plus";

//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false);

//定义数据模型
const registData = ref({
  username: "",
  password: "",
  rePassword: "",
});

//校验密码的函数
const checkRePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请再次输入密码"));
  } else if (value !== registData.value.password) {
    callback(new Error("两次输入密码不一致!"));
  } else {
    callback();
  }
};

//定义表单校验规则
const rules = {
  username: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { min: 5, max: 16, message: "长度在 5 到 16 个字符", trigger: "blur" },
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 10, max: 16, message: "长度在 5 到 16 个字符", trigger: "blur" },
  ],
  rePassword: [
    { validator: checkRePassword, trigger: "blur" }, //自定义校验规则，要在表单校验规则前面声明
  ],
};

//调用后台啊接口完成注册
import { userRegisterService, userLoginService } from "@/api/user.js";
const register = async () => {
  let result = await userRegisterService(registData.value);
  // if (result.code === 0) {
  //   alert(result.msg ? result.msg : "注册成功");
  // } else {
  //   alert("注册失败");
  // }
  // alert(result.msg ? result.msg : "注册成功");
  ElMessage.success(result.msg ? result.msg : "注册成功");
};

import { useRouter } from "vue-router";
import { useTokenStore } from "@/stores/token.js";
const router = useRouter();
const tokenStore = useTokenStore();
const login = async () => {
  //调用接口，完成登录
  let result = await userLoginService(registData.value);

  // if (result.code === 0) {
  //   alert(result.msg ? result.msg : "登录成功");
  // } else {
  //   alert("登录失败");
  // }
  // alert(result.msg ? result.msg : "登录成功");
  ElMessage.success(result.msg ? result.msg : "登录成功");
  //把得到的token存储到pinia中
  tokenStore.setToken(result.data);

  //跳转到首页,路由完成跳转
  router.push("/");
};

//在点击切换表单时，清空表单数据
const resetForm = () => {
  registData.value = {
    username: "",
    password: "",
    rePassword: "",
  };
};
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- 注册表单 -->
      <el-form
        ref="form"
        size="large"
        autocomplete="off"
        v-if="isRegister"
        :model="registData"
        :rules="rules"
      >
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            :prefix-icon="User"
            placeholder="请输入用户名"
            v-model="registData.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            :prefix-icon="Lock"
            type="password"
            placeholder="请输入密码"
            v-model="registData.password"
          ></el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input
            :prefix-icon="Lock"
            type="password"
            placeholder="请输入再次密码"
            v-model="registData.rePassword"
          ></el-input>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item>
          <el-button
            class="button"
            type="primary"
            auto-insert-space
            @click="register"
          >
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link
            type="info"
            :underline="false"
            @click="
              isRegister = false;
              resetForm();
            "
          >
            ← 返回
          </el-link>
        </el-form-item>
      </el-form>
      <!-- 登录表单 -->
      <el-form
        ref="form"
        size="large"
        autocomplete="off"
        v-else
        :model="registData"
        :rules="rules"
      >
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            :prefix-icon="User"
            placeholder="请输入用户名"
            v-model="registData.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            name="password"
            :prefix-icon="Lock"
            type="password"
            placeholder="请输入密码"
            v-model="registData.password"
          ></el-input>
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex">
            <el-checkbox>记住我</el-checkbox>
            <el-link type="primary" :underline="false">忘记密码？</el-link>
          </div>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button
            class="button"
            type="primary"
            auto-insert-space
            @click="login"
            >登录</el-button
          >
        </el-form-item>
        <el-form-item class="flex">
          <el-link
            type="info"
            :underline="false"
            @click="
              isRegister = true;
              resetForm();
            "
          >
            注册 →
          </el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url("@/assets/logo2.png") no-repeat 60% center / 240px auto,
      url("@/assets/login_bg.jpg") no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>