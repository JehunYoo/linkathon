<script setup lang="ts">
import SkillCategory from "@/components/Skill/SkillCategory.vue";

import {Builder} from "builder-pattern";
import {provide, ref,} from "vue";
import UserCategory from "@/components/User/Recruit/UserCategory.vue";
import SkillCategoryList from "@/components/Skill/SkillCategoryList.vue";
import UserList from "@/components/User/Recruit/UserList.vue";

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

const skillCategoryList: SkillCategory[] = [];

const dm2: SkillDTO[] = [];
dm2.push(Builder<SkillDTO>()
    .skillName("삐뽀스크립트")
    .skillId(4)
    .skillImgUrl("https://i.postimg.cc/C50Qnxmj/image.png")
    .build())
skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("언어")
        .skillList(dm2)
        .build());

skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("프론트엔드")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("백엔드")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("테스팅 툴")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("데이터베이스")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("데이터")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("데브옵스")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("협업툴")
        .skillList(dummySkillList)
        .build());

skillCategoryList.push(
    Builder<SkillCategory>()
        .categoryName("디자인")
        .skillList(dummySkillList)
        .build());

const dummy = Builder<MemberRecruit>()
    .selectedCategory(-1)
    .skillCategoryList(skillCategoryList)
    .selectSkillId(new Set<number>())
    .build()

const updateSelectedCategory = (newCategory: number) => {
  refDummy.value.selectedCategory = newCategory;
};

const updateSelectSkillId = (skillId: number) => {
  if (refDummy.value.selectSkillId.has(skillId))
    refDummy.value.selectSkillId.delete(skillId)
  else
    refDummy.value.selectSkillId.add(skillId)
}

const refDummy = ref<MemberRecruit>(dummy);
provide('skillCategorySelect', refDummy);
provide('updateSelectedCategory', updateSelectedCategory);
provide('updateSelectSkillId', updateSelectSkillId);

</script>

<template>
  <div class="recruit-vue-container">
    <div style="flex: 2">
      <div class="page-title">기술 스택</div>
      <SkillCategory/>
    </div>
    <UserCategory/>
  </div>
  <SkillCategoryList/>
  <div class="hr"/>
  <UserList/>
</template>

<style scoped>
.page-title {
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 16px;
}

.recruit-vue-container {
  padding-top: 48px;
  display: flex;
  flex-wrap: wrap;
  gap: 25px;
  align-items: flex-start;
}

@media screen and (max-width: 768px) {
  .recruit-vue-container {
    flex-direction: column;
  }
}

.hr {
  border-bottom: #7D3BFF 1px solid;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>