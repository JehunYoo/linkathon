<script setup lang="ts">
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";
import {ref, Ref} from "vue";
import {ProjectInfoDTO} from "@/dto/projectDTO.ts";
import ProjectStore from "@/store/ProjectStore.ts";
import {ProjectService} from "@/api/ProjectService.ts";

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
//
// const starRef: Ref<Boolean>[] = []
//
// for (let i = 0; i < dummy?.length; i++) {
//   starRef.push(ref(dummy[i].starred))
// }
//
// const starClick = (v: Ref<Boolean>) => {
//   v.value = !v.value;
// }

const projectService: ProjectService = ProjectStore.getters.getProjectService;
const projectsRef: Ref<ProjectInfoDTO[]> = ref([]); // 반응형 배열 ref 객체로 선언
const starRef: Ref<Boolean>[] = [];

const bind = async () => {
  projectsRef.value = await projectService.getMyProjects();
  for (let i = 0; i < projectsRef.value?.length; i++) {
    starRef.push(ref(projectsRef.value[i].starred));
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
  <h1>내 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="projectsRef" :star-click="starClick" :star-ref="starRef"/>
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