import { defineStore } from "pinia";
import { ref } from "vue";

const useUserInfoStore = defineStore(
  "usefInfo",
  () => {
    //定义状态相关的函数
    const info = ref({});

    const setInfo = (newInfo) => {
      info.value = newInfo;
    };

    const removeInfo = () => {
      info.value = {};
    };
    return { info, setInfo, removeInfo };
  },
  { persist: true }
);

export default useUserInfoStore;
