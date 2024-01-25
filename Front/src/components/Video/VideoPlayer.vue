<script setup lang="ts">
import sample1 from "@/assets/another.png";
import sample2 from "@/assets/my.png";
</script>

<template>
  <div class="video-play-container">
    <img :src="sample1" alt="" class="video">
    <div class="myVideo-container">
      <img :src="sample2" alt="" class="myVideo">
    </div>
  </div>
</template>

<style scoped>
.video-play-container {
  position: relative;
}

.video {
  border-radius: 10px;
  width: 100%;
  object-fit: cover;
}

.myVideo {
  max-width: 300px;
  width: 20vw;
  border-radius: 10px;
}

.myVideo-container {
  position: absolute;
  top: 1%;
  right: 1%;
  display: flex;
}
</style>
