<script setup lang="ts">
import {PropType} from "vue";
import StarButton from "@/components/ProjectCard/StarButton.vue";

const props = defineProps({
  dataList: Object as PropType<ProjectInfo[]>
});

</script>

<template>
  <div v-for="data in props.dataList" class="project-card">
    <div>
      <img :src="data?.imgSrc" alt="">
    </div>
    <div class="right-box-container">
      <div class="star-container">
        <StarButton :check="data.myStar"/>
        <span class="star-text">{{ data?.star }}</span>
      </div>
      <div class="project-name">{{ data?.projectName }}</div>
      <div class="project-content">{{ data?.projectContent }}</div>
    </div>
  </div>
</template>

<style scoped>
.project-name {
  margin-top: 6px;
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
}

.project-content {
  line-height: 18px;
  margin-top: 9px;
  color: #303030;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
}

.star-text {
  color: #303030;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  margin-left: 6px;
  line-height: 15px;
}

.star-container {
  display: flex;
  justify-content: right;
}

.right-box-container {
  flex: 1;
  margin-left: 8px;
}

img {
  width: 100px;
  height: 100px;
  border-radius: 5px;
  object-fit: cover;
}

.project-card {
  padding: 12px;
  flex: 1;
  min-width: 315px;
  height: 124px;
  border-radius: 10px;
  border: 1px solid #DEDEDE;
  display: flex;
  transition: transform 1s ease;
}

.project-card:hover {
  scale: 1.02;
  border: #7d3bff 1px solid;
  box-shadow: 4px 4px 6px 0 rgba(0, 0, 0, 0.25);
}
</style>