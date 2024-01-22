<script setup lang="ts">
import {ref} from "vue";
import {Builder} from "builder-pattern";
import SkillCategory from "@/components/Skill/SkillCategory.vue";

const dummySkillList: SkillDTO[] = [];
dummySkillList.push(Builder<SkillDTO>()
    .skillName("JavaScript")
    .skillId(1)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build());
dummySkillList.push(Builder<SkillDTO>()
    .skillName("MjScript")
    .skillId(2)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build());
dummySkillList.push(Builder<SkillDTO>()
    .skillName("TypeScript")
    .skillId(3)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build());

const skillCategoryList: SkillCategoryDTO[] = [];
const skillCategoryListRef = ref<SkillCategoryDTO[]>(skillCategoryList);

skillCategoryList.push(
    Builder<SkillCategoryDTO>()
        .categoryName("언어")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategoryDTO>()
        .categoryName("프론트엔드")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategoryDTO>()
        .categoryName("백엔드")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategoryDTO>()
        .categoryName("테스팅 툴")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategoryDTO>()
        .categoryName("데이터베이스")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategoryDTO>()
        .categoryName("데이터")
        .skillList(dummySkillList)
        .build());
</script>

<template>
  <div v-for="skillList in skillCategoryListRef">
    {{ skillList }}
  </div>
</template>

<style scoped>

</style>