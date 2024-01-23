<script setup lang="ts">
import {inject} from "vue";
import SkillIcon from "@/components/Skill/SkillIcon.vue";

const skillCategorySelect: SkillCategorySelect = inject('skillCategorySelect')!;
const updateSelectSkillId: Function = inject<Function>('updateSelectSkillId')!;
</script>

<template>
  <div v-if="skillCategorySelect.skillCategoryList[skillCategorySelect.selectedCategory]" class="skill-list-container">
    <div v-for="skillDTO in skillCategorySelect.skillCategoryList[skillCategorySelect.selectedCategory].skillList"
         class="skill-button-container"
         @click="updateSelectSkillId(skillDTO.skillId)">
      <SkillIcon :skill="skillDTO" width="40px" height="40px" radius="10px"/>
      <div class="skill-button-text">{{ skillDTO.skillName }}</div>
    </div>
  </div>
</template>

<style scoped>


.skill-list-container {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-top: 16px;
}

.skill-button-container {
  display: flex;
  min-width: 148px;
  gap: 16px;
}

.skill-button-text {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 40px;
}
</style>