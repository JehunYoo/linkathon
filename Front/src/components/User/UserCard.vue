<script lang="ts" setup>
import Tier from "@/components/Tier.vue";
import SkillIcon from "@/components/Skill/SkillIcon.vue";
import {Builder} from "builder-pattern";

const props = defineProps({
  t: {
    type: Boolean,
    default: false
  }
});
const dummySkill = Builder<SkillDTO>()
    .skillName("")
    .skillImgUrl("data:image/png;base64,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")
    .build();
</script>

<template>
  <div :class="{'t':!props.t}" class="user-card">
    <img alt="" src="https://thumb.mtstarnews.com/06/2023/06/2023060322064338985_2.jpg/dims/optimize">
    <div class="text-container">
      <div class="info-text">
        카리나
        <Tier :rating="300" font-size="14px" height="16px" style="margin-left: 8px" width="28px"></Tier>
      </div>
      <div class="introduce-text">
        안녕하세요. 백엔드 개발자입니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번...
      </div>
      <div style="display: flex; gap:4px; flex: 1">
        <SkillIcon :skill="dummySkill" height="20px" radius="4px" width="20px"/>
        <SkillIcon :skill="dummySkill" height="20px" radius="4px" width="20px"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.text-container {
  display: flex;
  flex-direction: column;
}

img {
  object-fit: cover;
  width: 76px;
  height: 76px;
  border-radius: 5px;
  margin-right: 8px;
}

.introduce-text {
  flex: 1;
  color: #303030;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 16px;
  margin-top: 4px;
  margin-bottom: 4px;
}

.info-text {
  flex: 1;
  display: flex;
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
}

.user-card {
  display: flex;
  padding: 12px;
  min-width: 310px;
  width: 100%;
  flex: 1;
  height: 100px;
  border-radius: 10px;
  border: 1px solid #DEDEDE;
  transition: transform 0.3s ease;
}

.t:hover {
  transform: scale(1.01);
  border: #7d3bff 1px solid;
  box-shadow: 4px 4px 6px 0 rgba(0, 0, 0, 0.25);
}
</style>