<script setup lang="ts">

</script>

<template>
  <div class="blur"/>
</template>

<style scoped>
.blur {
  z-index: 3;
  position: absolute;
  left: 50%;
  top: 50%;
  width: 100vw;
  height: 100vh;
  background: rgba(255, 255, 255, 0.70);
  backdrop-filter: blur(5px);
  transform: translate(-50%, -50%)
}
</style>