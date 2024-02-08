<script setup lang="ts">

import SkillIcon from "@/components/Skill/SkillIcon.vue";
import Tier from "@/components/Tier.vue";
import {Builder} from "builder-pattern";


const dummySkill = Builder<SkillDTO>()
    .skillName("JavaScript")
    .skillImageUrl("data:image/png;base64,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")
    .build();
</script>

<template>
  <div class="title-container">
    <h1>내정보</h1>
    <RouterLink to="/editInfo" class="remove-button">수정</RouterLink>
  </div>
  <div class="profile">
    <img alt="" src="https://thumb.mtstarnews.com/06/2023/06/2023060322064338985_2.jpg/dims/optimize" class="img">
    <div class="text-container">
      <div class="info-text">
        카리나
        <Tier :rating="300" font-size="24px" height="28px" style="margin-left: 12px" width="48px" radius="10px"></Tier>
      </div>
      <div class="introduce-text">
        참고 URL : https://github.com/MaSeongJin
      </div>
      <div class="introduce-text">
        안녕하세요. 백엔드 개발자입니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다.
      </div>
    </div>
  </div>
  <div class="my-skill-container">
    <h1>기술 스택</h1>
    <h2>백엔드</h2>
    <div class="skill-container">
      <div class="skill-box" v-for="_ in 8">
        <SkillIcon :skill="dummySkill" height="56px" radius="10px" width="56px"/>
        <h3>{{ dummySkill.skillName }}</h3>
      </div>
    </div>
    <h2>백엔드</h2>
    <div class="skill-container">
      <div class="skill-box" v-for="_ in 8">
        <SkillIcon :skill="dummySkill" height="56px" radius="10px" width="56px"/>
        <h3>{{ dummySkill.skillName }}</h3>
      </div>
    </div>
  </div>
</template>

<style scoped>
@media screen and (max-width: 530px) {
  .img {
    display: none;
  }
}
.my-skill-container {
  margin-bottom: 60px;
}
.skill-container {
  display: flex;
  gap: 26px;
  flex-wrap: wrap;
  justify-content: left;
  max-width: 700px;
  margin-bottom: 20px;
}

.skill-box {
  display: flex;
  gap: 12px;
}

h3 {
  line-height: 55px;
}

h2 {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  margin-bottom: 16px;
}

.text-container {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.introduce-text {
  max-width: 440px;
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: 22px;
}

.profile {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
}

.img {
  border-radius: 10px;
  width: 200px;
  height: 200px;
}

.info-text {
  display: flex;
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 28px;
}

h1 {
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  flex: 1;
  margin-bottom: 20px;
}

.title-container {
  display: flex;
}

.remove-button {
  width: 80px;
  height: 34px;
  border-radius: 5px;
  background: #FF6161;
  border: #FF6161 solid 1px;
  color: #F2F2F2;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: 33px;
  transition: color 0.3s ease;
}

.remove-button:hover {
  color: #FF6161;
  background: white;
}
</style>