<script  setup>
import { ref } from "vue";

//导入个人信息
import useUserInfoStore from "@/stores/userInfo.js";
const userInfoStore = useUserInfoStore();

const pasds = ref({
  old_pwd: "",
  new_pwd: "",
  re_pwd: "",
});

//校验密码的函数
const checkRePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请再次输入新密码"));
  } else if (value !== pasds.value.new_pwd) {
    callback(new Error("两次输入密码不一致!"));
  } else {
    callback();
  }
};

//定义规则
const rules = {
  old_pwd: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 10, max: 16, message: "长度在 10 到 16 个字符", trigger: "blur" },
  ],
  new_pwd: [
    { required: true, message: "请输入新密码", trigger: "blur" },
    { min: 10, max: 16, message: "长度在 10 到 16 个字符", trigger: "blur" },
  ],
  re_pwd: [
    { required: true, validator: checkRePassword, trigger: "blur" }, //自定义校验规则，要在表单校验规则前面声明
  ],
};

import { useRouter } from "vue-router";
const router = useRouter();
import { userPasswordUpdateService } from "@/api/user.js";
import { ElMessage } from "element-plus";

const submitPassword = async () => {
  try {
    // 调用异步函数，等待结果
    let result = await userPasswordUpdateService(pasds.value);

    // 处理结果
    ElMessage.success(result?.msg || "修改成功");

    // 更新 pinia 中的数据
    userInfoStore.info.password = pasds.value.new_pwd;
    router.push("/login");
  } catch (error) {
    // 处理错误
    console.error("密码更新失败", error);
    ElMessage.error("密码更新失败");
  }
};

const cleanPasds = () => {
  pasds.value = {
    old_pwd: "",
    new_pwd: "",
    re_pwd: "",
  };
};
</script>

<template>
  <div style="margin: 20px" />
  <el-form
    :label-position="labelPosition"
    label-width="100px"
    :model="pasds"
    :rules="rules"
    style="max-width: 460px"
  >
    <el-form-item label="登录名称">
      <el-input disabled v-model="userInfoStore.info.username" />
    </el-form-item>
    <el-form-item label="旧密码" prop="old_pwd">
      <el-input
        type="password"
        v-model="pasds.old_pwd"
        placeholder="请先输入旧密码"
        show-password
      />
    </el-form-item>
    <el-form-item label="新密码" prop="new_pwd">
      <el-input
        type="password"
        v-model="pasds.new_pwd"
        placeholder="请输入新密码"
        show-password
      />
    </el-form-item>
    <el-form-item label="确认新密码" prop="re_pwd">
      <el-input
        type="password"
        placeholder="请再确认您的新密码"
        v-model="pasds.re_pwd"
        show-password
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitPassword">提交</el-button>
      <el-button @click="cleanPasds">取消修改</el-button>
    </el-form-item>
  </el-form>
</template>

  