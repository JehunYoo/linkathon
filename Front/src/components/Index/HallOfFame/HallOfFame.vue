<script lang="ts" setup>

import IndexSubMenu from "@/components/Index/IndexSubMenu.vue";
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";
import {ref, Ref} from "vue";
import {ProjectInfoDTO} from "@/dto/projectDTO.ts";
import {ProjectService} from "@/api/ProjectService.ts";
import ProjectStore from "@/store/projectStorage.ts";

const projectService: ProjectService = ProjectStore.getters.getProjectService;

const popularProjectsRef: Ref<ProjectInfoDTO[]> = ref([]);

const bind = async () => {
  popularProjectsRef.value = (await projectService.getPopularProjects(0, 4)).projects;
};

bind();

</script>

<template>
  <IndexSubMenu style="margin-top: 48px" title="명예의 전당"/>
  <div class="fame-container">
    <ProjectCard :data-list="popularProjectsRef"/>
  </div>
</template>

<style scoped>
.fame-container {
  margin-top: 20px;
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  gap: 20px;
}
</style>