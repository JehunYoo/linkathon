<script lang="ts" setup>

import IndexSubMenu from "@/components/Index/IndexSubMenu.vue";
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";

import {Builder} from "builder-pattern";

const tempDummy: ProjectInfoDTO = Builder<ProjectInfoDTO>()
    .projectName("프로젝트 주제")
    .projectDesc("프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로")
    .starCount(10)
    .projectId(1)
    .starred(true)
    .projectUrl("https://yt3.googleusercontent.com/v1IJmuo9h3-2-CADo_MyPuVbcLEmZkNVr0oko3WKnUvyF0ffYbNjAVYB7RC6tXDG422BiER69Uw=s900-c-k-c0x00ffffff-no-rj")
    .build();
const dummy: ProjectInfoDTO[] = [];
dummy.push(tempDummy);
dummy.push(tempDummy);
dummy.push(tempDummy);
dummy.push(tempDummy);

</script>

<template>
  <IndexSubMenu style="margin-top: 48px" title="명예의 전당"/>
  <div class="fame-container">
    <ProjectCard :data-list="dummy"/>
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