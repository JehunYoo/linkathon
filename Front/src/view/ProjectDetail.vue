<script lang="ts" setup>

import ProjectCenter from "@/components/Project/ProjectCenter.vue";
import {Builder} from "builder-pattern";
import SkillIcon from "@/components/Skill/SkillIcon.vue";
import ProjectTeam from "@/components/Project/ProjectTeam.vue";
import ProjectLink from "@/components/Project/ProjectLink.vue";
import {onMounted, Ref, ref, watch} from "vue";
import {ProjectDetailDto, ProjectRequestDto} from "@/dto/projectDTO.ts";
import ProjectStore from "@/store/projectStorage.ts";
import {ProjectService} from "@/api/ProjectService.ts";
import {useRoute} from "vue-router";
import router from "@/router";

const route = useRoute();


// TODO: 팀 아이디로 스킬셋 목록 가져오는 요청 필요
const dummySkillList: SkillDTO[] = [];
const dummySkill: SkillDTO = Builder<SkillDTO>()
    .skillId(1)
    .skillName("JavaScript")
    .skillImgUrl("https://images.velog.io/images/mokyoungg/post/6659a8e8-5234-49e5-b3da-a3816c08bfdc/%ED%83%80%EC%9E%85%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%20%EB%A1%9C%EA%B3%A0.svg").build();

for (let i = 0; i < 4; i++) {
  dummySkillList.push(dummySkill);
}

const dummy: SkillCategory = Builder<SkillCategory>()
    .categoryName("백엔드")
    .skillList(dummySkillList)
    .build();

const dummyList: SkillCategory[] = [];
for (let i = 0; i < 3; i++) {
  dummyList.push(dummy);
}
dummyList.push(Builder<SkillCategory>()
    .categoryName("프론트엔드")
    .skillList(dummySkillList)
    .build())

const projectService: ProjectService = ProjectStore.getters.getProjectService;
const projectDetail: Ref<ProjectDetailDto> = ref({} as ProjectDetailDto);

const projectRequestDto: ProjectRequestDto = Builder<ProjectRequestDto>()
    .build();

const projectImg = ref();

const updateProject = (key: string, url: string) => {
  if (key === 'projectUrl')
    projectRequestDto.projectUrl = projectDetail.value.projectUrl = url;
  else if (key === 'deployUrl') {
    projectRequestDto.deployUrl = projectDetail.value.deployUrl = url;
  }
  projectService.updateProject(projectDetail.value.projectId, projectRequestDto, null);
}

const init = async () => {
  try {
    projectDetail.value = await projectService.getProjectDetail(parseInt(route.params.id as string));
  } catch (error) {
    alert("잘못된 링크입니다!!");
    await router.push('/');
  }
  projectRequestDto.projectName = projectDetail.value.projectName;
  projectRequestDto.teamId = projectDetail.value.teamId;
  projectRequestDto.projectDesc = projectDetail.value.projectDesc;
  projectRequestDto.projectUrl = projectDetail.value.projectUrl;
  projectRequestDto.deployUrl = projectDetail.value.deployUrl
}

// FIXME: 내 프로젝트가 하나도 없을 경우 처리 필요

onMounted(() => init());
watch(() => route.path, () => init());

// TODO: 해당 팀을 소유한 리더인지 확인 필요
const isLeader = ref(false);

</script>


<template>
  <div class="detail-container">
    <div class="side-container">
      <img class="project-image"
           :src="projectDetail.imgSrc"
           ref="projectImg">
      <ProjectLink :project-detail="projectDetail" :update-project="updateProject" :editable="isLeader"/>
    </div>
    <project-center :project-detail="projectDetail" :editable="isLeader"/>
    <div class="side-container">
      <div>
        <h1>기술스택</h1>
        <div v-for="data in dummyList" class="stack-container">
          <h2>{{ data.categoryName }}</h2>
          <div class="skill-list-container">
            <div v-for="skill in data.skillList">
              <SkillIcon :skill="skill" width="28px" height="28px" radius="3px" style="border-bottom: 1px solid"/>
            </div>
          </div>
        </div>
      </div>
      <!-- TODO: 프로젝트 팀 아이디 넘겨서 팀 목록 호출하기 -->
      <ProjectTeam/>
    </div>
  </div>
</template>


<style scoped>


.stack-container {
  margin-top: 10px;
  display: flex;
  gap: 20px;
  max-width: 250px;
  width: 100%;
}

h1 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 28px;
}

h2 {
  margin-left: 14px;
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 500;
  line-height: 28px;
  white-space: nowrap;
  width: 60px;
}

.skill-list-container {
  margin-bottom: 20px;
  max-width: 250px;
  flex: 1;
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
}

img {
  width: 250px;
  height: 250px;
  object-fit: cover;
  border-radius: 10px;
}

.side-container {
  min-width: 250px;
  height: max-content;
}

@media screen and (max-width: 943px) {
  .detail-container {
    flex-direction: column;
  }

  .project-image {
    width: 100%;
  }
}

.detail-container {
  display: flex;
  gap: 20px;
}
</style>

