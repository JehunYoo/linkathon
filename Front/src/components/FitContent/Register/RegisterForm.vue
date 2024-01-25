<script lang="ts" setup></script>

<template>
  <h1>회원 정보 입력</h1>
  <div class="register-container">
    <div class="register-content-block">
      <h2>이메일</h2>
      <div class="register-content-detail">
        <input class="input-text" type="email">
        <input class="register-button" style="width: 94px;" type="button" value="이메일 인증">
      </div>
    </div>
    <div class="register-content-block">
      <h2>비밀번호</h2>
      <input class="input-text" type="password">
    </div>
    <div class="register-content-block">
      <h2>비밀번호 확인</h2>
      <input class="input-text" type="password">
    </div>
    <div class="register-content-block">
      <h2>이름</h2>
      <input class="input-text" type="text">
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
        <input class="input-text" type="text">-
        <input class="input-text" type="text">-
        <input class="input-text" type="text">
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
      <input class="register-button" type="button" value="가입하기">
    </div>
  </div>
</template>

<style scoped>
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
  line-height: normal;
  transition: color 0.3s ease;
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