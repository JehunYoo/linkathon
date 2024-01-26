<script setup lang="ts">
import {ref} from "vue";
import {UserService} from "@/api/UserService.ts";
import {Builder} from "builder-pattern";

const userService = new UserService();

const email = ref<string>('');
const pw = ref<string>('');

const login = function () {
  const user = Builder<UserDTO>()
      .userId(email.value)
      .password(pw.value)
      .build();
  userService.login(user);
}
</script>

<template>
  <div class="login-box">
    Hello World
    <input type="email" placeholder="id" v-model="email">
    <input type="password" placeholder="password" v-model="pw">
    <input type="button" @click="login()" value="로그인">
  </div>
</template>

<style scoped>

.login-box input {
  display: block;
}
</style>