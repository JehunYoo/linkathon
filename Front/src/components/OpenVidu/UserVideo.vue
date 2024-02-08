<script setup lang="ts">

import {computed} from "vue";
import OvVideo from "@/components/OpenVidu/OvVideo.vue";

const clientData = computed(() => {
  const { clientData } = getConnectionData();
  return clientData;
});

const props = defineProps({
  streamManager: Object,
});

const getConnectionData = () => {
  const { connection } = props.streamManager.stream;
  return JSON.parse(connection.data);
}

</script>

<template>
  <div v-if="props.streamManager">
    <ov-video :stream-manager="props.streamManager"/>
    <div><p>{{ clientData }}</p></div>
  </div>
</template>

<style scoped>

</style>
