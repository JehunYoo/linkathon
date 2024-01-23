<script setup lang="ts">
import {PropType} from "vue";

const props = defineProps({
  skill: Object as PropType<Skill>,
  width: String,
  height: String,
  radius: String,
  fontSize:String,
});
</script>

<template>
  <div class="hover-container" :style="{ width: width, height: height,borderRadius: radius, fontSize:fontSize }">
    <div class="year-text" v-if="props.skill?.year">
      {{ props.skill?.year + "Y" }}
    </div>
    <img :src="props.skill?.imgSrc" :style="{ width: width, height: height,borderRadius: radius }">
  </div>
</template>

<style scoped>
.hover-container {
  position: relative;
}

img {
  object-fit: cover;
  position: absolute;
  z-index: 1;
  width: 100%;
  height: 100%;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.15);
}

.hover-container:hover img {
  filter: brightness(0.7);
}

.year-text {
  font-family: "Jockey One", sans-serif;
  position: absolute;
  z-index: 3;
  color: #ECEEF0;
  opacity: 0;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.hover-container:hover .year-text {
  opacity: 1;
}
</style>