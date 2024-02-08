<script lang="ts" setup>

import ProjectCenter from "@/components/Project/ProjectCenter.vue";
import {Builder} from "builder-pattern";
import SkillIcon from "@/components/Skill/SkillIcon.vue";
import ProjectTeam from "@/components/Project/ProjectTeam.vue";
import ProjectLink from "@/components/Project/ProjectLink.vue";
import {Ref, ref} from "vue";
import {ProjectDetailDto, ProjectRequestDto} from "@/dto/projectDTO.ts";
import ProjectStore from "@/store/ProjectStore.ts";
import {ProjectService} from "@/api/ProjectService.ts";

const dummySkillList: SkillDTO[] = [];
const dummySkill: SkillDTO = Builder<SkillDTO>()
    .skillId(1)
    .skillName("JavaScript")
    .skillImageUrl("https://images.velog.io/images/mokyoungg/post/6659a8e8-5234-49e5-b3da-a3816c08bfdc/%ED%83%80%EC%9E%85%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%20%EB%A1%9C%EA%B3%A0.svg").build();

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
const projectDetail: Ref<ProjectDetailDto> = ref(Builder<ProjectDetailDto>().build()) // 임시 데이터

const projectRequestDto: ProjectRequestDto = Builder<ProjectRequestDto>()
    .build();

const updateProject = (key: string, url: string) => {
  console.log(key, url);
  if (key === 'projectUrl')
    projectRequestDto.projectUrl = projectDetail.value.projectUrl = url;
  else if (key === 'deployUrl'){
    projectRequestDto.deployUrl = projectDetail.value.deployUrl = url;
  }
  projectService.updateProject(projectDetail.value.projectId, projectRequestDto);
}

const bind = async () => {
  // TODO: 프로젝트 번호를 상위에서 받아오기
  const dummyProjectId = 1;
  projectDetail.value = await projectService.getProjectDetail(dummyProjectId);
  projectRequestDto.projectName = projectDetail.value.projectName;
  projectRequestDto.teamId = projectDetail.value.teamId;
  projectRequestDto.projectDesc = projectDetail.value.projectDesc;
  projectRequestDto.projectUrl = projectDetail.value.projectUrl;
  projectRequestDto.deployUrl = projectDetail.value.deployUrl;
};

bind();
</script>


<template>
  <div class="detail-container">
    <div class="side-container">
      <img class="project-image"
           src="https://d34u8crftukxnk.cloudfront.net/slackpress/prod/sites/6/Project-management-steps2.ko-KR.png"
           alt="">
      <ProjectLink :project-detail="projectDetail" :update-project="updateProject"/>
    </div>
    <project-center :project-detail="projectDetail"/>
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

