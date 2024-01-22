<script setup lang="ts">
import {ref} from "vue";
import {Builder} from "builder-pattern";

const data: SkillDTO[][] = [];
const skillCategoryList = ref<SkillDTO[][]>(data);
const dummyList: skill[] = [];
dummyList.push(Builder<SkillDTO>()
    .skillName("JavaScript")
    .skillId(1)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build());
dummyList.push(Builder<SkillDTO>()
    .skillName("MjScript")
    .skillId(2)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build());
dummyList.push(Builder<SkillDTO>()
    .skillName("TypeScript")
    .skillId(3)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build());

skillCategoryList.value.push(dummyList);
skillCategoryList.value.push(dummyList);
skillCategoryList.value.push(dummyList);
</script>

<template>
  <div v-for="skillList in skillCategoryList">
    {{skillList}}
  </div>
</template>

<style scoped>

</style>