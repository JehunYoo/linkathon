<script setup lang="ts">

import ProjectAnalyse from "@/components/Project/ProjectAnalyse.vue";
</script>

<template>
  <div>
    <div class="title-container">
      <h1>프로젝트 명</h1>
      <div class="remove-button">프로젝트삭제</div>
    </div>
    <section>
      프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.
    </section>
    <ProjectAnalyse/>
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
}

section {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 125% */
}
</style>