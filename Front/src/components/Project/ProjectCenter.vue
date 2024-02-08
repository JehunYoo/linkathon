<script setup lang="ts">

import ProjectAnalyse from "@/components/Project/ProjectAnalyse.vue";
import GitAnalyse from "@/components/Project/GitAnalyse.vue";
import {ProjectService} from "@/api/ProjectService.ts";
import {onMounted, ref, Ref} from "vue";
import {PropType} from "vue";
import {ProjectDetailDto} from "@/dto/projectDTO.ts";

const props = defineProps({
  projectDetail: {
    type: Object as PropType<ProjectDetailDto>,
    required: true
  },
});

const projectService = new ProjectService();
const gitStatusRef : Ref<GitStatusDTO[]> = ref([]);
let totalCommits = 0;
onMounted(async () => {
  gitStatusRef.value = await projectService.getProjectContributions("jooyun-1", "Quicklog");
  console.log(gitStatusRef.value);
//@ts-nocheck
  for (let i = 0; i < gitStatusRef.value.length; i++) {
    totalCommits += gitStatusRef.value[i].commits;
  }
})

</script>

<template>
  <div>
    <div class="title-container">
      <h1> {{ props.projectDetail.projectName }} </h1>
      <div class="remove-button">프로젝트 삭제</div>
    </div>
    <section>
      {{ props.projectDetail.projectDesc }}
    </section>
    <ProjectAnalyse/>
    <GitAnalyse v-if="gitStatusRef" :gitStatus="gitStatusRef" :totalCommits="totalCommits"/>
  </div>

</template>

<style scoped>
.title-container {
  display: flex;
  margin-bottom: 20px;
}

section {
  margin-bottom: 28px;
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px;
}

h1 {
  margin-top: 10px;
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 10px;
  flex: 1;
}

.remove-button {
  color: #F2F2F2;
  text-align: center;
  font-size: 12px;
  font-style: normal;
  font-weight: 700;
  width: 87px;
  height: 28px;
  flex-shrink: 0;
  border-radius: 5px;
  background: #FF6161;
  border: 1px #FF6161 solid;
  line-height: 27px;
  transition: color 0.3s ease;
}

.remove-button:hover {
  color: #FF6161;
  background: white;
}

section {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 125% */
}
</style>