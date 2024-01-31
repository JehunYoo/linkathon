<script setup lang="ts">

import ContentFit from "@/components/Util/ContentFit.vue";
import SkillSelector from "@/components/SkillSelector.vue";
import FitDropDown from "@/components/FitContent/FitDropDown.vue";
</script>

<template>
  <ContentFit>
    <h1>회원 정보 수정</h1>
    <div class="holder">
      <h3>비밀번호</h3>
      <input class="text-input" type="password">
      <h3>비밀번호 확인</h3>
      <input class="text-input" type="password">
      <h3>이름</h3>
      <input class="text-input" type="text">
      <h3>생년월일</h3>
      <div class="detail-content-container">
        <input class="text-input" placeholder="년도(4자)" type="text">
        <input class="text-input" placeholder="월" type="text">
        <input class="text-input" placeholder="일" type="text">
      </div>

      <h3>성별</h3>
      <div class="detail-content-container">
        <div>
          <input name="gender" type="radio"/> 남자
        </div>
        <div>
          <input name="gender" type="radio"/> 여자
        </div>
      </div>

      <h2>기술</h2>
      <SkillSelector/>
      <h3>깃허브 링크</h3>
      <input class="text-input" type="text">

      <h3>분야</h3>
      <FitDropDown/>

      <h3>관련 경력</h3>
      <div class="detail-content-container">
        <div class="text-input" style="width: 100%; display: flex;">
          <input placeholder="기술명을 입력해주세요" style="border: none; font-size: 16px; width: 100%" type="text">
          <div class="year" style="width: 40px">년차</div>
        </div>
        <div class="button pp">경력인증</div>
      </div>

      <h3>프로필 이미지</h3>
      <div class="detail-content-container">
        <input class="text-input" type="text">
        <div class="button pp">파일 추가</div>
      </div>
      <div style="text-align: left">
        <div class="detail-content-container" style="margin-bottom: 7px">
          <h2>팀 구하기 등록</h2>
          <h4>등록하신 정보가 팀원 구하기 페이지에 등록됩니다.</h4>
        </div>
        <div class="detail-content-container" style="margin-bottom: 7px">
          <input type="radio">네 등록할래요
          <input type="radio">다음에 등록할게요.
        </div>
      </div>
    </div>
    <div class="button pp" style="margin-top: 20px">수정하기</div>
  </ContentFit>
</template>

<style scoped>
.holder {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.pp {
  background: #7D3BFF;
  color: #F2F2F2;
}

.pp:hover {
  background: white;
  color: #303030;
}

.button {
  transition: color 0.3s ease;
  border-radius: 5px;
  border: 1px solid #7D3BFF;
  height: 40px;
  line-height: 39px;
  flex: 1;
  padding-left: 20px;
  padding-right: 20px;
  min-width: max-content;
}

h1 {
  color: #303030;
  text-align: center;
  font-size: 28px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

h2 {
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-align: left;
}

h3 {
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-align: left;
}

h4 {
  margin-top: 5px;
  color: #606060;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.text-input {
  width: 100%;
  height: 40px;
  border-radius: 5px;
  border: 1px solid #303030;
  padding: 10px;
  color: #2b2b2b;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.detail-content-container {
  display: flex;
  gap: 20px;
}

input {
  outline: none;
}

</style>