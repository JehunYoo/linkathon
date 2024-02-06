<script setup lang="ts">
import SkillCategory from "@/components/Skill/SkillCategory.vue";

import {Builder} from "builder-pattern";
import {onMounted, provide, Ref, ref, watch,} from "vue";
import UserCategory from "@/components/User/Recruit/UserCategory.vue";
import SkillCategoryList from "@/components/Skill/SkillCategoryList.vue";
import UserList from "@/components/User/Recruit/UserList.vue";
import {SkillCategoryResponseDto} from "@/dto/tmpDTOs/SkillTypeDTO.ts";
import {TeamBuildingService} from "@/api/TeamBuildingService.ts";
import {TeamMemberFindDTO} from "@/dto/tmpDTOs/teamBuildingDTO.ts";
import {useRoute, useRouter} from "vue-router";
const router = useRouter();

const teamBuildingService = new TeamBuildingService();
const refSkillCategory: Ref<SkillCategoryResponseDto | undefined> = ref();
const refUser: Ref<TeamMemberFindDTO | undefined> = ref();
const skillCategoryList: Ref<SkillCategory[]> = ref([]);
const route = useRoute();

onMounted(async () => {
  refSkillCategory.value = await teamBuildingService.getAllSkillType();
  refUser.value = await teamBuildingService.getAllTeamFindMember({
    page: isNaN(parseInt(<string>route.query.page)) ? 1 : parseInt(<string>route.query.page), size: 4
  });
  if (refSkillCategory.value) {
    const skillTypeResponseDtoMap = refSkillCategory.value;

    for (const category in skillTypeResponseDtoMap) {
      if (Object.prototype.hasOwnProperty.call(skillTypeResponseDtoMap, category)) {
        const skillList = skillTypeResponseDtoMap[category];
        skillCategoryList.value.push(
            Builder<SkillCategory>()
                .categoryName(category)
                .skillList(skillList)
                .build()
        );
      }
    }
  }
})


const memberRecruit = Builder<MemberRecruit>()
    .selectedCategory(-1)
    .skillCategoryList(skillCategoryList.value)
    .selectSkillId(new Set<number>())
    .build()
const refMemberRecruit = ref<MemberRecruit>(memberRecruit);

const updateSelectedCategory = (newCategory: number) => {
  refMemberRecruit.value.selectedCategory = newCategory;
};

const updateSelectSkillId = async (skillId: number) => {
  if (refMemberRecruit.value.selectSkillId.has(skillId))
    refMemberRecruit.value.selectSkillId.delete(skillId)
  else
    refMemberRecruit.value.selectSkillId.add(skillId)

  refUser.value = await teamBuildingService.getAllTeamFindMember({
    skillIds: Array.from(refMemberRecruit.value.selectSkillId),
    page: isNaN(parseInt(<string>route.query.page)) ? 1 : parseInt(<string>route.query.page),
    size: 4

  });
    await router.push("/recruit?page=1");
};

provide('skillCategorySelect', refMemberRecruit);
provide('updateSelectedCategory', updateSelectedCategory);
provide('updateSelectSkillId', updateSelectSkillId);

watch(
    () => route.query.page,
    async (newPage, oldPage) => {
      if (newPage !== oldPage) {
        refUser.value = await teamBuildingService.getAllTeamFindMember({
          skillIds: Array.from(refMemberRecruit.value.selectSkillId),
          page: isNaN(parseInt(<string>route.query.page)) ? 1 : parseInt(<string>route.query.page), size: 4
        })
      }
    }
)


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
  <template v-if="refUser">
    <UserList :refUser="refUser"/>
  </template>
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