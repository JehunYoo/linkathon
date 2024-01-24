<script lang="ts" setup>

import Tier from "@/components/Tier.vue";
import ModalButton from "@/components/Modal/ModalButton.vue";
import ModalGithubButton from "@/components/Modal/ModealGithubButton.vue";
import ModalSkill from "@/components/Modal/ModalSkill.vue";
</script>

<template>
  <div class="member-modal-container">
    <img alt="" src="https://thumb.mtstarnews.com/06/2023/06/2023060322064338985_2.jpg/dims/optimize">
    <div class="member-info-container">
      <div class="member-info">
        <div class="member-name">카리나</div>
        <div class="member-workflow">3년차 백엔드 개발자</div>
        <Tier :rating="200" font-size="24px" height="28px" radius="10px" width="48px"/>
      </div>
      <div class="member-introduce">
        안녕하세요. 백엔드 개발자입니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다.
      </div>
      <div class="button-wrapper">
        <div class="button-container">
          <div class="git-button">
            <ModalGithubButton/>
          </div>
          <div class="button-right-container">
            <ModalButton button-text="합류 요청"/>
          </div>
        </div>
      </div>
    </div>
  </div>
  <h2 style="padding-left: 23px">기술 스택</h2>
  <div class="member-skill-container">
    <div class="skill-list-container">
      <ModalSkill color="#303030" font-size="16px" font-weight="400" margin-bottom="12px" title="백엔드"/>
      <ModalSkill color="#303030" font-size="16px" font-weight="400" margin-bottom="12px" style="justify-content: end"
                  title="프론트엔드"/>
    </div>
    <div class="skill-list-container">
      <ModalSkill color="#303030" font-size="16px" font-weight="400" margin-bottom="12px" title="데이터베이스"/>
      <ModalSkill color="#303030" font-size="16px" font-weight="400" margin-bottom="12px" style="justify-content: end"
                  title="인프라"/>
    </div>
  </div>
</template>

<style scoped>
.skill-list-container {
  display: flex;
  gap: 26px;
  margin-bottom: 16px;
}

h2 {
  color: #303030;
  font-size: 21px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 16px;
}

.member-skill-container {
  max-height: 185px;
  overflow: scroll;
  margin-bottom: 23px;
  padding: 0 24px 0px;
}

* {
  -ms-overflow-style: none;
}

::-webkit-scrollbar {
  display: none;
}

@media screen and (max-width: 458px) {
  .git-button {
    display: none;
  }
}

@media screen and (max-width: 698px) {
  .member-skill-container {
    height: max-content;
    max-height: max-content;
  }
}

@media screen and (max-width: 768px) {
  .member-workflow {
    display: none;
  }
}

.button-right-container {
  flex: 1;
  display: flex;
  justify-content: flex-end;
}

.member-modal-container {
  padding: 24px;
  display: flex;
}

img {
  width: 196px;
  height: 196px;
  border-radius: 5px;
}

.member-info-container {
  margin-left: 18px;
  margin-top: 26px;
  display: flex;
  flex-direction: column;
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
  max-width: 382px;
  margin-top: 14px;
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  max-height: 80px;
  overflow: scroll;
}

.button-wrapper {
  display: flex;
  flex: 1;
  justify-content: flex-end;
}

.button-container {
  display: flex;
  align-items: flex-end;
  width: 100%;
}
</style>
