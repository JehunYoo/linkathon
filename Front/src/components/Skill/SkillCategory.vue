<script lang="ts" setup>
import {inject} from "vue";

const skillCategorySelect: SkillCategorySelect = inject('skillCategorySelect')!;
const updateCategorySelect: Function = <Function>inject('updateSelectedCategory');

</script>

<template>
  <div>
    <div class="skill-category-container" v-if="skillCategorySelect?.skillCategoryList">
      <div v-for="(skillList, i) in skillCategorySelect?.skillCategoryList"
           :class="{'non-select':skillCategorySelect.selectedCategory !== i, 'select':skillCategorySelect.selectedCategory === i}"
           class="skill-category-box"
           @click="updateCategorySelect(i)">
        {{ skillList.categoryName }}
      </div>
    </div>

  </div>
</template>

<style scoped>

.skill-category-container {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.skill-category-box {
  padding: 9px 15px 10px;
  border-radius: 18px;
  white-space: nowrap;
  transition: color 0.3s ease;
}

.non-select {
  border: 1px solid #606060;
}

.select {
  border: 1px solid #7D3BFF;
  background: #7D3BFF;
  color: #DEDEDE;
}
</style>