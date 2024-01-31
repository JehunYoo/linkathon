<script lang="ts" setup>
import {PropType} from "vue";

const props = defineProps({
  skill: Object as PropType<SkillDTO>,
  width: String,
  height: String,
  radius: String,
  fontSize: String,
});
</script>

<template>
  <div v-if="props.skill?.skillYear"
       :style="{ width: width, height: height,borderRadius: radius, fontSize:fontSize }"
       class="normal-container hover-container">
    <div v-if="props.skill?.skillYear" class="year-text">
      {{ props.skill?.skillYear + "Y" }}
    </div>
    <img :src="props.skill?.skillImgUrl" :style="{ width: width, height: height,borderRadius: radius }" alt="">
  </div>
  <div v-else :style="{ width: width, height: height,borderRadius: radius, fontSize:fontSize }"
       class="normal-container">
    <img :src="props.skill?.skillImgUrl" :style="{ width: width, height: height,borderRadius: radius }" alt="">
  </div>
</template>

<style scoped>
.normal-container {
  position: relative;
}

img {
  object-fit: cover;
  position: absolute;
  z-index: 1;
  width: 100%;
  height: 100%;
  border: 0.5px solid #606060;
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