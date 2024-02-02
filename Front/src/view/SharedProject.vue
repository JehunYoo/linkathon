<script setup lang="ts">
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";
import Pagination from "@/components/Pagination.vue";
import {ref, Ref} from "vue";
import {ProjectInfoDTO} from "@/dto/projectDTO.ts";
import {ProjectService} from "@/api/ProjectService.ts";

const projectService: ProjectService = new ProjectService();

const projectsRef: Ref<ProjectInfoDTO[]> = ref([]); // 반응형 배열 ref 객체로 선언
const starRef: Ref<Boolean>[] = [];

const popularProjectsRef: Ref<ProjectInfoDTO[]> = ref([]);
const popularStarRef: Ref<Boolean>[] = [];

const bind = async () => {
  projectsRef.value = await projectService.getALlProjects();
  for (let i = 0; i < projectsRef.value?.length; i++) {
    starRef.push(ref(projectsRef.value[i].starred));
  }

  popularProjectsRef.value = await projectService.getPopularProjects();
  for (let i = 0; i < projectsRef.value?.length; i++) {
    popularStarRef.push(ref(projectsRef.value[i].starred));
  }
};


const starClick = (v: Ref<Boolean>, projectId: number) => {
  if (v.value) {
    projectService.unlikeProject(projectId).then(() => {
      v.value = !v.value;
    });
  } else {
    projectService.likeProject(projectId).then(() => {
      v.value = !v.value;
    });
  }
}

bind();

</script>

<template>
  <h1>인기 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="popularProjectsRef" :star-click="starClick" :star-ref="popularStarRef"/>
  </div>
  <h1>공유 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="projectsRef" :star-click="starClick" :star-ref="starRef"/>
  </div>
  <!-- TODO: 페이지네이션 적용하기 -->
  <Pagination style="margin-bottom: 60px"/>
</template>

<style scoped>
h1 {
  margin-top: 48px;
  color: #000;
  font-size: 24px;
  font-style: normal;
  font-weight: 400;
}

.project-container {
  margin-top: 20px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px
}
</style>