//导入请求工具request.js
import request from "../utils/request";

//提供调用注册接口的函数
export const userRegisterService = (registerData) => {
  //借助UrlSearchParams完成传递
  const params = new URLSearchParams();
  for (let key in registerData) {
    params.append(key, registerData[key]);
  }
  return request.post("/user/register", params);
};

export const userLoginService = (loginData) => {
  const params = new URLSearchParams();
  for (let key in loginData) {
    params.append(key, loginData[key]);
  }
  return request.post("/user/login", params);
};

//获取用户详细信息
export const getUserInfoService = () => {
  return request.get("/user/userInfo");
};

//修改个人信息
export const userInfoUpdateService = (userInfo) => {
  return request.put("/user/update", userInfo);
};

//修改头像
export const userAvatarUpdateService = (avatarUrl) => {
  const params = new URLSearchParams();
  params.append("avatarUrl", avatarUrl);
  return request.patch("/user/updateAvatar", params);
};
//修改密码
export const userPasswordUpdateService = (params) => {
  return request.patch("/user/updatePwd", params);
};
