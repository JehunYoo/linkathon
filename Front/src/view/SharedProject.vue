<script setup lang="ts">
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";
import Pagination from "@/components/Pagination.vue";
import {ref, Ref, watch} from "vue";
import {ProjectInfoDTO} from "@/dto/projectDTO.ts";
import {ProjectService} from "@/api/ProjectService.ts";
import ProjectStore from "@/store/projectStorage.ts";
import {useRoute} from "vue-router";

const route = useRoute();
const projectService: ProjectService = ProjectStore.getters.getProjectService;

const projectsRef: Ref<ProjectInfoDTO[]> = ref([]); // 반응형 배열 ref 객체로 선언
const popularProjectsRef: Ref<ProjectInfoDTO[]> = ref([]);
const pageableRef: Ref<PageableDto> = ref({pageNumber: 1, totalPages: 5} as PageableDto);

const bind = async () => {
  popularProjectsRef.value = (await projectService.getPopularProjects(0, 4)).projects;

  const projects = await projectService.getALlProjects(parseInt(route.query.page as string) - 1, 5);
  projectsRef.value = projects.projects;
  pageableRef.value = projects.pageable;
};
watch([() => route.query], () => bind());
bind();
</script>

<template>
  <h1>인기 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="popularProjectsRef" />
  </div>
  <h1>공유 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="projectsRef" />
  </div>
  <Pagination :pageable-d-t-o="pageableRef"/>
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