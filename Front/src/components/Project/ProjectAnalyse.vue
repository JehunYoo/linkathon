<script setup lang="ts">
import ProjectFrontAnalyse from "@/components/Project/ProjectFrontAnalyse.vue";
import ProjectBackendAnalyse from "@/components/Project/ProjectBackendAnalyse.vue";
defineProps({
  editable: {
    type: Boolean,
    default: true
  },
});
</script>

<template>
  <div class="chart-list">
    <div class="container">
      <ProjectFrontAnalyse :editable="editable"/>
    </div>
    <div class="container">
      <ProjectBackendAnalyse :editable="editable"/>
    </div>
  </div>
</template>

<style scoped>


@media screen and (max-width: 1304px) {
  .chart-list {
    flex-direction: column;
  }
}

@media screen and (max-width: 768px) {
  .chart-list {
    flex-direction: column;
  }
}

.chart-list {
  display: flex;
  gap: 18px;
}

.container {
  flex: 1;
}
</style>