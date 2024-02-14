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
import {TeamBuildingService} from "@/api/TeamBuildingService.ts";
import {TeamMemberResponseDto, TeamSkillDto} from "@/dto/tmpDTOs/teamDTO.ts";
import {SkillType} from "@/dto/tmpDTOs/commonDTO.ts";
import Modal from "@/components/Modal/Modal.vue";
import ModalAddDeployUrl from "@/components/Modal/ModalAddDeployUrl.vue";
import {LighthouseService} from "@/api/LighthouseService.ts";


interface TeamRefs {
  skillsRef: Ref<TeamSkillDto[]>
  membersRef: Ref<TeamMemberResponseDto[]>
  skillsMapRef: Ref<Map<SkillType, TeamSkillDto[]>>
}

const route = useRoute();

const teamBuildingService: TeamBuildingService = new TeamBuildingService();
const projectService: ProjectService = ProjectStore.getters.getProjectService;
const lighthouseService: LighthouseService = new LighthouseService();

const projectDetail: Ref<ProjectDetailDto> = ref(Builder<ProjectDetailDto>().build());
const projectRequestDto: ProjectRequestDto = Builder<ProjectRequestDto>()
    .build();
const teamRefs: TeamRefs = {
  skillsRef: ref([]),
  membersRef: ref([]),
  skillsMapRef: ref(new Map<SkillType, TeamSkillDto[]>()),
}
const isLeader = ref(false);
const projectImg = ref();
const deployUrls = ref<string[]>([]);

const updateUrlOfProject = async (key: string, url: string) => {
  const isHttpUrl = (url: string) => /^(http|https):\/\//i.test(url);
  if (!isHttpUrl(url))
    throw SyntaxError("도메인 이름 형식이 유효하지 않음.");

  if (key === 'projectUrl')
    projectRequestDto.projectUrl = projectDetail.value.projectUrl = url;
  else if (key === 'deployUrl') {
    projectRequestDto.deployUrl = projectDetail.value.deployUrl = url;
  }
  await projectService.updateProject(projectDetail.value.projectId, projectRequestDto, null);
}

const initLightHouseUrls = async () => {
  return deployUrls.value = await lighthouseService.getProjectUri(projectDetail.value.projectId, true);
}

const updateLightHouseUrls = async (urls: string[]) => {
  urls.forEach((url) => {
    if (!url || !url.startsWith('/'))
      throw SyntaxError();
  })
  await lighthouseService.updateProjectUri(projectDetail.value.projectId, urls);
}

const initTeamRefs = async (teamId: number) => {
  const teamResponseDto = await teamBuildingService.getTeamDetailByTeamId(teamId);
  teamRefs.skillsRef.value = teamResponseDto?.teamSkills;
  teamRefs.membersRef.value = teamResponseDto.members;
  const m = teamRefs.skillsMapRef.value;
  teamRefs.skillsRef.value.map((teamSkillDto) => {
    const t = teamSkillDto.skillType;
    if (!m.has(t))
      m.set(t, []);
    m.get(t)?.push(teamSkillDto);
  });
}

const init = async () => {
  try {
    projectDetail.value = await projectService.getProjectDetail(parseInt(route.params.id as string));
  } catch (error) {
    await router.push('/myPage?mode=3');
  }
  projectRequestDto.projectName = projectDetail.value.projectName;
  projectRequestDto.teamId = projectDetail.value.teamId;
  projectRequestDto.projectDesc = projectDetail.value.projectDesc;
  projectRequestDto.projectUrl = projectDetail.value.projectUrl;
  projectRequestDto.deployUrl = projectDetail.value.deployUrl
  await initTeamRefs(projectDetail.value.teamId);
  isLeader.value = await projectService.checkLeader(projectDetail.value.projectId);
  await initLightHouseUrls();
}

const registerDeployUrl = async (mainDeployUrl: string, deployUrls: string[]) => {
  projectDetail.value.deployUrl = mainDeployUrl;

  try{
    await updateUrlOfProject('deployUrl', mainDeployUrl);
  } catch (e) {
    if (e instanceof SyntaxError) {
      alert('올바른 도메인 주소를 입력해주세요.');
      return;
    }
  }
  try{
    await updateLightHouseUrls(deployUrls);
  } catch (e) {
    if (e instanceof SyntaxError) {
      alert('올바른 추가 경로를 입력해주세요.');
      return;
    }
  }
  alert("등록완료");
  await initLightHouseUrls();
  modalController();
}

// modal
const modalRef = ref<Boolean>(false);
const modalController = () => {
  modalRef.value = !modalRef.value
}

onMounted(() => init());
watch(() => route.path, () => init());

</script>


<template>
  <Modal v-if="modalRef" @closeModal="modalController">
    <ModalAddDeployUrl @register-deploy-url="registerDeployUrl" :main-deploy-url-prop="projectDetail.deployUrl" :deploy-urls-prop="deployUrls"/>
  </Modal>
  <div class="detail-container">
    <div class="side-container">
      <img class="project-image"
           :src="projectDetail.imgSrc"
           ref="projectImg">

      <ProjectLink :project-detail="projectDetail" :update-project="updateUrlOfProject" :editable="isLeader" @handle-project-url="modalController"/>
    </div>
    <project-center :project-detail="projectDetail" :editable="isLeader"/>
    <div class="side-container">
      <div>
        <h1>기술스택</h1>
        <div v-for="[key, value] in teamRefs.skillsMapRef?.value" class="stack-container">
          <h2>{{ key }}</h2>
          <div class="skill-list-container">
            <div v-for="skill in value">
              <SkillIcon
                  :skill="Builder<SkillDTO>()
                  .skillName(skill.skillName)
                  .skillType(skill.skillType)
                  .skillImgUrl(skill.skillImageUrl)
                  .build()" height="28px" radius="3px" style="border-bottom: 1px solid" width="28px"/>
            </div>
          </div>
        </div>
      </div>
      <ProjectTeam :team-member-dtos="teamRefs.membersRef.value"/>
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
  margin-left: 8px;
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 500;
  line-height: 28px;
  white-space: nowrap;
  width: 80px;
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

input {
  padding-left: 12px;
  height: 36px;
  flex: 1;
  color: #606060;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.link-content-container {
  display: flex;
  gap: 12px;
}

</style>

