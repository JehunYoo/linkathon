<script setup lang="ts">

import {onMounted} from "vue";

const props = defineProps({
  streamManager: Object,
});

onMounted(() => {
  props.streamManager.addVideo
});

</script>

<template>
  <video autoplay/>
</template>

<style scoped></style>

<!--<script>-->
<!--export default {-->
<!--  name: 'OvVideo',-->

<!--  props: {-->
<!--    streamManager: Object,-->
<!--  },-->

<!--  mounted () {-->
<!--    this.streamManager.addVideoElement(this.$el);-->
<!--  },-->
<!--};-->
<!--</script>-->
