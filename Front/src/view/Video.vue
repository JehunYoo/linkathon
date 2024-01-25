<script setup lang="ts">

import VideoPlayer from "@/components/Video/VideoPlayer.vue";
import VideoInfo from "@/components/Video/VideoInfo.vue";
import ModalEffect from "@/components/Modal/ModalEffect.vue";
</script>

<template>
  <div style="overflow: hidden; border-radius: 30px">
    <ModalEffect text="홍길동님과의 화상 채팅"/>
    <div class="video-container">
      <div class="video-sector">
        <VideoPlayer/>
      </div>
      <div class="introduce-container">
        <VideoInfo/>
      </div>
    </div>
  </div>


</template>

<style scoped>
@media screen and (max-width: 1240px) {
  .video-container {
    flex-direction: column;
  }
  .introduce-container {
    margin-bottom: 20px;
  }
}

.video-container {
  padding: 20px;
  display: flex;
  border-radius: 30px;
  border: 2px solid #7D3BFF;
  overflow: hidden;
}

.video-sector {
  flex: 6;
}

.introduce-container {
  padding-right: 20px;
  flex: 1;
}
</style>