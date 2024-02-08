<script setup lang="ts">

import {onMounted, ref} from "vue";

const {streamManager} = defineProps({
  streamManager: Object,
});

const videoEl = ref({});

onMounted(() => {
  streamManager.addVideo(videoEl);
});

</script>

<template>
  <video ref="videoEl" autoplay/>
</template>

<style scoped></style>
