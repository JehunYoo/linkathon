<script setup lang="ts">
import {ref} from "vue";
import {Builder} from "builder-pattern";

const data: Skill[][] = [];
const skillCategoryList = ref<Skill[][]>(data);
const dummyList: skill[] = [];
dummyList.push(Builder<Skill>()
    .skillName("JavaScript")
    .skillId(1)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build());
dummyList.push(Builder<Skill>()
    .skillName("MjScript")
    .skillId(2)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build());
dummyList.push(Builder<Skill>()
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