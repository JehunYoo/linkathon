<script setup lang="ts">
import { onMounted } from 'vue';
import { ApiService } from "@/api/ApiService.ts";
import router from "@/router";
import { httpStatusCode } from "@/util/httpStatus.ts";
import store from "@/store";

const apiService = new ApiService();
const url = "/api/users"

onMounted(async () => {
  try {

    // Axios를 사용하여 백엔드로 요청을 보냅니다.
    const response = await apiService.postData(false, `${url}/oauth2/access`,"");

    // 응답을 처리하거나 상태를 업데이트할 수 있습
    if (response && response.status === httpStatusCode.OK) {
      await store.dispatch("updateToken", response.headers['authorization']);
      await router.push('/')
    }

  } catch (error) {
    console.error('백엔드 요청 실패:', error);
  }
});
</script>

<template>
  <div>
    <!-- 내용이 있을 경우 여기에 표시할 내용을 추가하세요. -->
  </div>
</template>

<style scoped>
/* 필요한 스타일을 추가할 수 있습니다. */
</style>