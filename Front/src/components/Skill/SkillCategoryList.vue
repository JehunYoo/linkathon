<script lang="ts" setup>
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
      <SkillIcon :skill="skillDTO" height="40px" radius="10px" width="40px"/>
      <div :class="skillCategorySelect.selectSkillId.has(skillDTO.skillId)?'select-skill':'non-select-skill'"
           class="skill-button-text">
        {{ skillDTO.skillName }}
      </div>
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
  border-radius: 8px;
}


.select-skill {
  color: #7d3bff;
}

.non-select-skill {
  color: #303030;
}

.skill-button-text {
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 40px;
}
</style>