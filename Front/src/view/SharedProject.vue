<script setup lang="ts">
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";
import Pagination from "@/components/Pagination.vue";
import {ref, Ref} from "vue";
import {ProjectInfoDTO} from "@/dto/projectDTO.ts";
import {ProjectService} from "@/api/ProjectService.ts";

const projectService: ProjectService = new ProjectService();
let dummy: Ref<ProjectInfoDTO[]> = ref([]); // 반응형 배열 ref 객체로 선언
const starRef: Ref<Boolean>[] = [];

projectService.getALlProjects().then(projects => {
  bind(projects, dummy);
});

projectService.getPopularProjects().then(projects => {
  bind(projects, dummy);
})

const bind = (p: ProjectInfoDTO[], pref: Ref) => {
  pref.value = p;

  for (let i = 0; i < dummy.value?.length; i++) {
    starRef.push(ref(dummy.value[i].starred))
  }
}

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

// const tempDummy: ProjectInfoDTO = Builder<ProjectInfoDTO>()
//     .projectName("프로젝트 주제")
//     .projectDesc("프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로")
//     .starCount(10)
//     .projectId(1)
//     .starred(true)
//     .imgSrc("https://yt3.googleusercontent.com/v1IJmuo9h3-2-CADo_MyPuVbcLEmZkNVr0oko3WKnUvyF0ffYbNjAVYB7RC6tXDG422BiER69Uw=s900-c-k-c0x00ffffff-no-rj")
//     .build();
// const dummy: ProjectInfoDTO[] = [];
// dummy.push(tempDummy);
// dummy.push(tempDummy);
// dummy.push(tempDummy);
// dummy.push(tempDummy);


</script>

<template>
  <h1>인기 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="dummy" :star-click="starClick" :star-ref="starRef"/>
  </div>
  <h1>공유 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="dummy" :star-click="starClick" :star-ref="starRef"/>
  </div>
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