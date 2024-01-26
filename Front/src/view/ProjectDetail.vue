<script lang="ts" setup>

import ProjectCenter from "@/components/Project/ProjectCenter.vue";
</script>


<template>
  <div class="detail-container">
    <div class="side-container">
      <img class="project-image"
          src="https://d34u8crftukxnk.cloudfront.net/slackpress/prod/sites/6/Project-management-steps2.ko-KR.png"
          alt="">
    </div>
    <project-center/>
    <div class="side-container">
    </div>
  </div>
</template>


<style scoped>
img {
  width: 250px;
  height: 250px;
  object-fit: cover;
  border-radius: 10px;
}

.side-container {
  min-width: 250px;
  height: max-content;
}

@media screen and (max-width: 943px) {
  .detail-container {
    flex-direction: column;
  }
  .project-image {
    width: 100%;
  }
}

.detail-container {
  display: flex;
  gap: 20px;
}
</style>
