<script setup lang="ts">
import {Builder} from "builder-pattern";
import ProjectCard from "@/components/ProjectCard/ProjectCard.vue";
import {ref, Ref} from "vue";

const tempDummy: ProjectInfoDTO = Builder<ProjectInfoDTO>()
    .projectName("프로젝트 주제")
    .projectDesc("프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로")
    .starCount(10)
    .projectId(1)
    .starred(true)
    //.projectUrl("https://yt3.googleusercontent.com/v1IJmuo9h3-2-CADo_MyPuVbcLEmZkNVr0oko3WKnUvyF0ffYbNjAVYB7RC6tXDG422BiER69Uw=s900-c-k-c0x00ffffff-no-rj")
    .build();
const dummy: ProjectInfoDTO[] = [];
dummy.push(tempDummy);
dummy.push(tempDummy);
dummy.push(tempDummy);
dummy.push(tempDummy);

const starRef: Ref<Boolean>[] = []

for (let i = 0; i < dummy?.length; i++) {
  starRef.push(ref(dummy[i].starred))
}

const starClick = (v :Ref<Boolean>) => {
  v.value = !v.value;
}
</script>

<template>
  <h1>내 프로젝트</h1>
  <div class="project-container">
    <ProjectCard :data-list="dummy" :star-click="starClick" :star-ref="starRef"/>
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