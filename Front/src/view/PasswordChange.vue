<script lang="ts" setup>

import ContentFit from "@/components/Util/ContentFit.vue";
</script>

<template>
  <ContentFit>
    <h1>비밀번호 변경</h1>
    <div class="input-container">
      <input class="input-text" placeholder="비밀번호 입력" type="password">
      <input class="input-text" placeholder="비밀번호 재입력" type="password">
      <input class="button" type="button" value="비밀번호 변경">
    </div>

  </ContentFit>
</template>

<style scoped>

.input-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.input-text {
  border-radius: 5px;
  border: 1px solid #303030;
  height: 40px;
  width: 100%;
  padding: 10px;
  color: #606060;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.button {
  height: 40px;
  border-radius: 5px;
  background: #7D3BFF;
  border: #7D3CFF 1px solid;
  color: #F2F2F2;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  transition: color 0.3s ease;
}

.button:hover {
  background: white;
  color: #2b2b2b;
}


h1 {
  color: #303030;
  text-align: center;
  font-size: 28px;
  font-style: normal;
  font-weight: 600;
  line-height: normal;
  margin-bottom: 41px;
}

</style>
