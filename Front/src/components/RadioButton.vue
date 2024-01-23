<script setup lang="ts">
const props = defineProps({
  check: Boolean,
});
</script>

<template>
  <svg width="8" height="8" viewBox="0 0 8 8" fill="none" xmlns="http://www.w3.org/2000/svg" v-show="!props.check">
    <circle cx="4" cy="4" r="4" fill="#545454"/>
  </svg>
  <svg xmlns="http://www.w3.org/2000/svg" width="8" height="8" viewBox="0 0 8 8" fill="none" v-show="props.check">
    <circle cx="4" cy="4" r="4" fill="#FAFAFA"/>
  </svg>
</template>

<style scoped>
</style>