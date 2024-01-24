<script setup lang="ts">
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
  <div class="normal-container hover-container" :style="{ width: width, height: height,borderRadius: radius, fontSize:fontSize }"
       v-if="props.skill?.skillYear">
    <div class="year-text" v-if="props.skill?.skillYear">
      {{ props.skill?.skillYear + "Y" }}
    </div>
    <img :src="props.skill?.skillImgUrl" :style="{ width: width, height: height,borderRadius: radius }" alt="">
  </div>
  <div class="normal-container" :style="{ width: width, height: height,borderRadius: radius, fontSize:fontSize }" v-else>
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