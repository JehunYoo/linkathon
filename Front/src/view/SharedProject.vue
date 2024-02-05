<script setup lang="ts">
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";
import Pagination from "@/components/Pagination.vue";
import {ref, Ref, watch} from "vue";
import {ProjectInfoDTO} from "@/dto/projectDTO.ts";
import {ProjectService} from "@/api/ProjectService.ts";
import ProjectStore from "@/store/ProjectStore.ts";
import {useRoute} from "vue-router";

const route = useRoute();

const projectService: ProjectService = ProjectStore.getters.getProjectService;

const projectsRef: Ref<ProjectInfoDTO[]> = ref([]); // 반응형 배열 ref 객체로 선언
const starRef: Ref<Boolean>[] = [];

const popularProjectsRef: Ref<ProjectInfoDTO[]> = ref([]);
const popularStarRef: Ref<Boolean>[] = [];
const pageableRef: Ref<PageableDto> = ref({pageNumber:1, totalPages:5} as PageableDto);

const bind = async () => {
  popularProjectsRef.value = await projectService.getPopularProjects();
  for (let i = 0; i < popularProjectsRef.value?.length; i++) {
    popularStarRef.push(ref(popularProjectsRef.value[i].starred));
  }

  const projects = await projectService.getALlProjects(parseInt(route.query.page as string) - 1, 5);
  projectsRef.value = projects.closedProjects;
  for (let i = 0; i < projectsRef.value?.length; i++) {
    starRef.push(ref(projectsRef.value[i].starred));
  }
  pageableRef.value = projects.pageable;

};


const starClick = async (v: Ref<Boolean>, i: number, projectId: number) => {
  if (v.value) {
    await projectService.unlikeProject(projectId);
  } else {
    await projectService.likeProject(projectId);
  }
  v.value = !v.value;
}

watch([() => route.query], () => bind());
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
  <Pagination :pageable-d-t-o="pageableRef" />
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