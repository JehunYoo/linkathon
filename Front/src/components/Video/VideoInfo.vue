<script setup lang="ts">

import ModalGithubButton from "@/components/Modal/ModealGithubButton.vue";
import Tier from "@/components/Tier.vue";
import ModalSkill from "@/components/Modal/ModalSkill.vue";
</script>

<template>
  <div class="member-info-container">
    <div class="member-info">
      <div class="member-name">카리나</div>
      <Tier :rating="200" font-size="24px" height="28px" radius="10px" width="48px"/>
      <ModalGithubButton/>
    </div>
    <div class="member-workflow">3년차 백엔드 개발자</div>
    <div class="member-introduce">
      안녕하세요. 백엔드 개발자입니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다.
    </div>
    <div class="skill-list-container">
      <ModalSkill title="백엔드" margin-bottom="10px" font-size="18px" font-weight="500"/>
      <ModalSkill title="데이터베이스" margin-bottom="10px" font-size="18px" font-weight="500"/>
      <ModalSkill title="프론트엔드" margin-bottom="10px" font-size="18px" font-weight="500"/>
      <ModalSkill title="협업툴" margin-bottom="10px" font-size="18px" font-weight="500"/>
      <ModalSkill title="협업툴" margin-bottom="10px" font-size="18px" font-weight="500"/>
    </div>
    <div class="button">
      합류 요청
    </div>
  </div>
</template>

<style scoped>
@media screen and (max-width: 1240px) {
  .skill-list-container {
    flex-direction: row;
    flex-wrap: wrap;
    gap: 20px;
  }
}

@media screen and (min-width: 1241px) {
  .skill-list-container {
    flex-direction: row;
    flex-wrap: wrap;
    gap:12px;
  }
}

.skill-list-container {
  margin-top: 21px;
  margin-bottom: 22px;
  display: flex;
  overflow: auto;
  height: auto;
  max-height: 350px;
}

.button {
  height: 40px;
  width: 100%;
  border-radius: 10px;
  background: #7D3BFF;
  border: #7D3BFF solid 1px;
  color: #F2F2F2;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  text-align: center;
  line-height: 39px;
  transition: color 0.3s ease;
}

.button:hover {
  background: white;
  color: #2b2b2b;
}

h2 {
  color: #303030;
  font-size: 21px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 16px;
}

* {
  -ms-overflow-style: none;
}

::-webkit-scrollbar {
  display: none;
}


.member-info-container {
  min-width: 288px;
  width: 100%;
  margin-left: 18px;
  margin-top: 8px;
  display: flex;
  flex-direction: column;
  height: 100%;
}

.member-info {
  gap: 12px;
  display: flex;
}

.member-name {
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 28px;
}

.member-workflow {
  padding-top: 13px;
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
}

.member-introduce {
  margin-top: 14px;
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  max-height: max-content;
}
</style>