<script lang="ts" setup>

import FitDropDown from "@/components/FitContent/FitDropDown.vue";
import SkillSelectV from "@/components/SkillSelector.vue";
</script>

<template>
  <h1>추가정보 입력</h1>
  <div class="content-container">
    <div>
      <h2>기술</h2>
      <SkillSelectV/>
    </div>
    <div>
      <h2>깃허브 링크</h2>
      <input class="text-input" type="text">
    </div>
    <div>
      <h2>분야</h2>
      <FitDropDown/>
    </div>
    <div>
      <h2>관련 경력</h2>
      <div class="detail-content-container">
        <div class="text-input" style="width: 100%; display: flex;">
          <input placeholder="기술명을 입력해주세요" style="border: none; font-size: 16px; width: 100%" type="text">
          <div class="year" style="width: 40px">년차</div>
        </div>
        <div class="button pp">경력인증</div>
      </div>
    </div>
    <div>
      <h2>프로필 이미지</h2>
      <div class="detail-content-container">
        <input class="text-input" type="text">
        <div class="button pp">파일 추가</div>
      </div>
    </div>
    <div>
      <div class="detail-content-container" style="margin-bottom: 7px">
        <h2>팀 구하기 등록</h2>
        <h3>등록하신 정보가 팀원 구하기 페이지에 등록됩니다.</h3>
      </div>
      <div class="detail-content-container" style="margin-bottom: 7px">
        <input type="radio">네 등록할래요
        <input type="radio">다음에 등록할게요.
      </div>
    </div>
    <div class="detail-content-container">
      <router-link class="button wt" to="/">다음에 하기</router-link>
      <div class="button pp">등록하기</div>
    </div>
  </div>
</template>

<style scoped>
h3 {
  margin-top: 5px;
  color: #606060;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

input {
  outline: none;
}

.year {
  color: #606060;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.pp {
  background: #7D3BFF;
  color: #F2F2F2;
}

.pp:hover {
  background: white;
  color: #303030;
}

.wt {
  background: white;
  color: #303030;
}

.wt:hover {
  background: #7D3BFF;
  color: #F2F2F2;
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
  font-weight: 600;
  line-height: normal;
  margin-bottom: 40px;
}

h2 {
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-align: left;
  margin-bottom: 9px;
}

.content-container {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.detail-content-container {
  display: flex;
  gap: 20px;
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
</style>