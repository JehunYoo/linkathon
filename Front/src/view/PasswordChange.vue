<script lang="ts" setup>
import ContentFit from "@/components/Util/ContentFit.vue";
import {computed, ref} from "vue";
import router from "@/router";
import {UserService} from "@/api/UserService.ts";
import {Builder} from "builder-pattern";
import {ChangePasswordDTO} from "@/dto/ChangePasswordDTO.ts";
import Store from "@/store/index.ts";

const userService = new UserService();

const email = computed(() => String(Store.state.email));

const pw1 = ref<string>('');
const pw2 = ref<string>('');

const changePassword = function () {

  console.log(email.value)

  if(!email.value) {
    alert("과정에서 오류가 발생했습니다.");
  }

  if (pw1.value.length < 8 && pw1.value.length > 13) {
    alert("비밀번호는 8자리 이상, 13자리 미만으로 설정해주세요")
    return;
  }

  // 영어, 숫자, 특수문자 각각 하나 이상 포함
  if(!(/[a-zA-Z]/.test(pw1.value))){
    alert("비밀번호에 영어를 하나 이상 포함시켜야 합니다.")
    return;
  }
  //숫자를 하나 이상 포합
  if(!(/\d/.test(pw1.value))){
    alert("비밀번호에 숫자를 하나 이상 포함시켜야 합니다.")
    return;
  }
  if(!(/[!@#$%^&*()_+{}\[\]:;<>,.?~\\-]/.test(pw1.value))){
    alert("비밀번호에 특수문자를 하나 이상 포함시켜야 합니다.")
    return;
  }
  if(pw1.value !== pw2.value) {
    alert("비밀번호가 일치하지 않습니다.")
    return;
  }

  const data = Builder<ChangePasswordDTO>()
      .email(email.value)
      .password(pw1.value)
      .build();

  userService.changePassword(data);

  router.push("/login");
}
</script>

<template>
  <ContentFit>
    <h1>비밀번호 변경</h1>
    <div class="input-container">
      <input v-model="pw1" class="input-text" placeholder="비밀번호 입력" type="password">
      <input v-model="pw2" class="input-text" placeholder="비밀번호 재입력" type="password">
      <input @click = "changePassword" class="button" type="button" value="비밀번호 변경">
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
