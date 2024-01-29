<script lang="ts" setup>
import {ref} from "vue";

const mode = ref<Boolean>(true)
const modeUpdate = (bool: boolean) => {
  mode.value = bool;
}
</script>

<template>
  <h1>아이디 / 비밀번호 찾기</h1>
  <div class="option-select-container">
    <div :class="mode?'selected':'non-selected'" class="select" @click="modeUpdate(true)">
      아이디 찾기
    </div>
    <div :class="!mode?'selected':'non-selected'" class="select" @click="modeUpdate(false)">
      비밀번호 찾기
    </div>
  </div>
  <div v-if="mode" class="input-container">
    <input class="input-text" placeholder="생년월일 입력" type="text">
    <input class="input-text" placeholder="이름 입력" type="text">
    <input class="input-text" placeholder="핸드폰 번호 입력" type="text">
    <input class="register-button" type="button" value="아이디 찾기">
  </div>
  <div v-if="!mode" class="input-container">
    <div class="content-detail">
      <input class="input-text" placeholder="이메일 입력" type="text">
      <input class="register-button" style="width: 114px;" type="button" value="인증번호 발송">
    </div>
    <div class="content-detail">
      <input class="input-text" placeholder="인증번호 입력" type="text">
      <input class="register-button" style="width: 94px" type="button" value="인증">
    </div>
    <router-link class="register-button" to="/passwordChange">비밀번호 찾기</router-link>
  </div>
</template>

<style scoped>
input:focus {
  outline: #7D3CFF 1px solid;
}
.content-detail {
  display: flex;
  gap: 20px;
}

.input-container {
  margin-top: 40px;
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

.register-button {
  height: 40px;
  border-radius: 5px;
  background: #7D3BFF;
  border: #7D3CFF 1px solid;
  color: #F2F2F2;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: 39px;
  transition: color 0.3s ease;
}

.register-button:hover {
  background: white;
  color: #2b2b2b;
}

.select {
  flex: 1;
  text-align: center;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  padding-bottom: 12px;
}

.selected {
  color: #303030;
  border-bottom: #7D3CFF 2px solid;
}

.non-selected {
  color: #909090;
  border-bottom: #909090 2px solid;
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

.option-select-container {
  display: flex;
  justify-content: center;
}
</style>