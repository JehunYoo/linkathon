<script lang="ts" setup>
import {ref} from "vue";
import {UserService} from "@/api/UserService.ts";
import {Builder} from "builder-pattern";
import {SendEmailRequestDTO} from "@/dto/SendEmailRequestDTO.ts";

const userService = new UserService();

const email = ref<string>('');
const pw1 = ref<string>('');
const pw2 = ref<string>('');
const name = ref<string>('');
const firstNumber = ref<string>('');
const secondNumber = ref<string>('');
const lastNumber = ref<string>('');
const year = ref<number>();
const month = ref<number>();
const day = ref<number>();
const gender = ref<boolean>();

const verification = ref<boolean>(false);
const currentYear = new Date().getFullYear();// 올해 연도

const register = function () {


  // if (!verification.value) {
  //   //이메일 인증하라고 해야함
  //   alert("이메일 인증을 완료하세요")
  //   return;
  // }

  //유효성 검증
  //이메일
  const emailPattern: RegExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  if (!emailPattern.test(email.value)) {
    alert("이메일의 형식이 올바르지 않습니다.")
    return
  }

  //비밀번호
  //8자리 이상
  //영어 + 숫자 + 특수문자 각각 하나 이상 조합
  if (pw1.value.length < 8 && pw1.value.length > 13) {
    alert("비밀번호는 8자리 이상, 13자리 미만으로 설정해주세요")
    return false;
  }

  // 영어, 숫자, 특수문자 각각 하나 이상 포함
  if(!(/[a-zA-Z]/.test(pw1.value))){
    alert("비밀번호에 영어를 하나 이상 포함시켜야 합니다.")
    return
  }
  //숫자를 하나 이상 포합
  if(!(/\d/.test(pw1.value))){
    alert("비밀번호에 숫자를 하나 이상 포함시켜야 합니다.")
    return
  }
  if(!(/[!@#$%^&*()_+{}\[\]:;<>,.?~\\-]/.test(pw1.value))){
    alert("비밀번호에 특수문자를 하나 이상 포함시켜야 합니다.")
    return
  }

  if (pw1.value !== pw2.value) {
    alert("비밀번호가 일치하지 않습니다.")
    return
  }

  if(!name.value){
    alert("이름을 입력해주세요")
    return
  }

  //생일
  //연도값 확인
  //연도는 크게 제약 X
  if (!year || year.value === undefined || year.value < 1900 || year.value > currentYear) {
    alert("생일(연도)을 정확이 입력해주세요")
    return;
  }
  //월은 1 이상 12 이하
  if (!month || month.value === undefined || month.value < 1 || month.value > 12) {
    alert("생일(월)을 정확이 입력해주세요")
    return;
  }
  //일 제한
  if (!day || day.value === undefined || day.value < 1 || day.value > 31) {
    alert("생일(일)을 정확이 입력해주세요")
    return;
  }
  //4, 6, 9, 11월은 30일임
  if ((month.value == 4 || month.value == 6 || month.value == 9 || month.value == 11) && day.value == 31) {
    alert("생일(일)을 정확이 입력해주세요")
    return;
  }
  //2월일 때는 연도에 따라서 29일
  if (month.value == 2) {
    const isLeap = (year.value % 4 == 0 && (year.value % 100 != 0 || year.value % 400 == 0));

    if (day.value > 29 || (day.value == 29 && !isLeap)) {
      alert("생일(일)을 정확이 입력해주세요")
      return;
    }
  }
  //핸드폰 번호
  if(firstNumber.value !== "010" || secondNumber.value.length != 4 || lastNumber.value.length != 4){
    alert("전화번호가 제대로 입력되었는지 확인해주세요")
    return
  }
  else {
    if(!(/\d/.test(firstNumber.value)) || !(/\d/.test(secondNumber.value)) || !(/\d/.test(lastNumber.value)) ) {
      alert("전화번호에는 숫자만 입력해주세요")
      return
    }
  }

  if (!gender || gender.value === undefined){
    alert("성별을 선택해주세요")
    return
  }

  const user = Builder<UserSignUpDto>()
      .email(email.value)
      .password(pw1.value)
      .name(name.value)
      .birth(new Date(year.value.toString() + "-" + month.value.toString() + "-" + day.value.toString()))
      .phoneNumber(firstNumber.value + secondNumber.value + lastNumber.value)
      .gender(gender.value)
      .build();
  userService.sign(user);
}

const validEmail = async function () {

  if (!email.value) return;

  const data = Builder<SendEmailRequestDTO>()
      .email(email.value)
      .build();

  verification.value = await userService.verificationEmail(data);

}
</script>

<template>
  <h1>회원 정보 입력</h1>
  <div class="register-container">
    <div class="register-content-block">
      <h2>이메일</h2>
      <div class="register-content-detail">
        <input v-model="email" class="input-text" type="email">
        <input class="register-button" style="width: 94px;" type="button" value="이메일 인증" @click="validEmail">
      </div>
    </div>
    <div class="register-content-block">
      <h2>비밀번호</h2>
      <input v-model="pw1" class="input-text" type="password">
    </div>
    <div class="register-content-block">
      <h2>비밀번호 확인</h2>
      <input v-model="pw2" class="input-text" type="password">
    </div>
    <div class="register-content-block">
      <h2>이름</h2>
      <input v-model="name" class="input-text" type="text">
    </div>
    <div class="register-content-block">
      <h2>생년월일</h2>
      <div class="register-content-detail">
        <input v-model="year" class="input-text" placeholder="년도(4자)" type="text">
        <input v-model="month" class="input-text" placeholder="월" type="text">
        <input v-model="day" class="input-text" placeholder="일" type="text">
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
          <input v-model="gender" name="gender" type="radio" value="true"/> 남자
        </div>
        <div>
          <input v-model="gender" name="gender" type="radio" value="false"/> 여자
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