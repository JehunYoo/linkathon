<script lang="ts" setup>
import {ref} from "vue";
import {UserService} from "@/api/UserService.ts";
import {Builder} from "builder-pattern";

const userService = new UserService();

const email = ref<string>('');
const pw = ref<string>('');
const name = ref<string>('');
const firstNumber = ref<string>('');
const secondNumber = ref<string>('');
const lastNumber = ref<string>('');
const register = function () {
  const user = Builder<UserSignUpDto>()
      .email(email.value)
      .password(pw.value)
      .name(name.value)
      .birth(new Date())
      .phoneNumber(firstNumber.value+secondNumber.value+lastNumber.value)
      .build();
  userService.sign(user);
}
</script>

<template>
  <h1>회원 정보 입력</h1>
  <div class="register-container">
    <div class="register-content-block">
      <h2>이메일</h2>
      <div class="register-content-detail">
        <input v-model="email" class="input-text" type="email">
        <input class="register-button" style="width: 94px;" type="button" value="이메일 인증">
      </div>
    </div>
    <div class="register-content-block">
      <h2>비밀번호</h2>
      <input v-model="pw" class="input-text" type="password">
    </div>
    <div class="register-content-block">
      <h2>비밀번호 확인</h2>
      <input class="input-text" type="password">
    </div>
    <div class="register-content-block">
      <h2>이름</h2>
      <input v-model="name" class="input-text" type="text">
    </div>
    <div class="register-content-block">
      <h2>생년월일</h2>
      <div class="register-content-detail">
        <input class="input-text" placeholder="년도(4자)" type="text">
        <input class="input-text" placeholder="월" type="text">
        <input class="input-text" placeholder="일" type="text">
      </div>
    </div>
    <div class="register-content-block">
      <h2>핸드폰 번호</h2>
      <div class="register-content-detail" style="gap: 6px; line-height: 39px">
        <input v-model="firstNumber" class="input-text" type="text">-
        <input v-model="secondNumber" class="input-text" type="text">-
        <input v-model="lastNumber" class="input-text" type="text">
      </div>
    </div>
    <div class="register-content-block">
      <h2>성별</h2>
      <div class="register-content-detail">
        <div>
          <input name="gender" type="radio"/> 남자
        </div>
        <div>
          <input name="gender" type="radio"/> 여자
        </div>
      </div>
    </div>
    <div class="register-content-block">
      <div class="register-button" @click="register">가입하기</div>
    </div>
  </div>
</template>

<style scoped>
input:focus {
  outline: #7D3CFF 1px solid;
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
  transition: color 0.3s ease;
  line-height: 39px;
}

.register-button:hover {
  background: white;
  color: #2b2b2b;
}

.register-content-detail {
  display: flex;
  gap: 20px;
}

h1 {
  color: #303030;
  text-align: center;
  font-size: 28px;
  font-style: normal;
  font-weight: 600;
  line-height: normal;
  margin-bottom: 45px;
}

h2 {
  text-align: left;
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.register-container {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.register-content-block {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.input-text {
  border-radius: 5px;
  border: 1px solid #303030;
  height: 40px;
  width: 100%;
  padding: 10px;
  font-size: 16px;
}
</style>