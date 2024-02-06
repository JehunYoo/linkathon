<script setup lang="ts">

import ContentFit from "@/components/Util/ContentFit.vue";
import {UserService} from "@/api/UserService.ts";
import router from "@/router";

const userService = new UserService();
const resignation = function () {
  userService.resignation();
  router.push("/");
}
</script>

<template>
  <ContentFit>
    <h1>탈퇴하시겠습니까?</h1>
    <div class="button-container">
      <div @click = "resignation" class="yes-button">네 탈퇴할게요.</div>
      <router-link to="/myPage" class="no-button">아니오</router-link>
    </div>
  </ContentFit>
</template>

<style scoped>
.button-container {
  display: flex;
  gap: 20px;
  justify-content: center;
}
h1 {
  color: #303030;
  text-align: center;
  font-size: 28px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  margin-bottom: 39px;
}

.yes-button, .no-button {
  width: 147px;
  height: 40px;
  border-radius: 5px;
  text-align: center;
  font-size: 16px;
  font-style: normal;
  font-weight: 500;
  line-height: 39px;
  transition: color 0.3s ease;
}

.yes-button {
  border: 1px solid #7D3BFF;
  color: #303030;
}

.no-button {
  background: #7D3BFF;
  border: 1px solid #7D3BFF;
  color: #F2F2F2;
}

.yes-button:hover {
  color: #F2F2F2;
  background: #7D3BFF;
}

.no-button:hover {
  color: #7D3BFF;
  background: white;
}
</style>