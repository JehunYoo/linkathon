<script setup lang="ts">
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";
import {onMounted, ref, Ref} from "vue";
import {ProjectInfoDTO} from "@/dto/projectDTO.ts";
import ProjectStore from "@/store/projectStorage.ts";
import {ProjectService} from "@/api/ProjectService.ts";

const projectService: ProjectService = ProjectStore.getters.getProjectService;
const projectsRef: Ref<ProjectInfoDTO[]> = ref([]); // 반응형 배열 ref 객체로 선언

onMounted(async () => {
  projectsRef.value = (await projectService.getMyProjects()).projects;
})

</script>

<template>
  <h1>내 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="projectsRef" />
  </div>
</template>

<style scoped>
h1 {
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 20px;
}

.project-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}
</style>