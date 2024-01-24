<script setup lang="ts">
import SkillIcon from "@/components/Skill/SkillIcon.vue";
import {Builder} from "builder-pattern";

const dummySkillList: SkillIcon[] = [];
const dummySkill: SkillDTO = Builder<SkillDTO>()
    .skillId(1)
    .skillName("TypeScript")
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .skillYear("10")
    .build()
for (let i = 0; i < 10; i++) {
  dummySkillList.push(dummySkill);
}

defineProps({
  color: String,
  fontSize: String,
  fontWeight: String,
  marginBottom: String,
  title: String,
});
</script>

<template>
  <div class="modal-skill-container">
    <div :style="{
      color:color,
      fontSize:fontSize,
      fontWeight:fontWeight,
      marginBottom:marginBottom
    }">
      {{ title }}
    </div>
    <div class="skill-container">
      <template v-for="skill in dummySkillList">
        <SkillIcon width="47px" height="47px" radius="10px" :skill="skill" font-size="28px"></SkillIcon>
      </template>
    </div>
  </div>

</template>

<style scoped>
.modal-skill-container {
  width: 100%;
}

.skill-container {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}
</style>