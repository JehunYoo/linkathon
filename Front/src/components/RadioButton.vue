<script lang="ts" setup>
const props = defineProps({
  check: Boolean,
});
</script>

<template>
  <svg v-show="!props.check" fill="none" height="8" viewBox="0 0 8 8" width="8" xmlns="http://www.w3.org/2000/svg">
    <circle cx="4" cy="4" fill="#545454" r="4"/>
  </svg>
  <svg v-show="props.check" fill="none" height="8" viewBox="0 0 8 8" width="8" xmlns="http://www.w3.org/2000/svg">
    <circle cx="4" cy="4" fill="#FAFAFA" r="4"/>
  </svg>
</template>

<style scoped>
</style>