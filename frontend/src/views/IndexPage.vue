<script setup>

import router from "@/router/index.js";
import {logout} from "@/net/index.js";

function userLogout(){
  logout(()=> router.push('/'))
}
</script>

<template>
  <div>
    <el-button plain type="warning" @click="userLogout()">退出登陆</el-button>
  </div>
</template>

<style scoped>

</style>