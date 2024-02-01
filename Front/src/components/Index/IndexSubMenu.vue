<script lang="ts" setup>
const props = defineProps({
  title: String
});
</script>

<template>
  <div class="hackathon-banner-title">
    {{ props.title }}
    <svg fill="none" height="16" viewBox="0 0 10 16" width="10" xmlns="http://www.w3.org/2000/svg">
      <path d="M2 14L8 8L2 2" stroke="#303030" stroke-linecap="round" stroke-linejoin="round" stroke-width="3"/>
    </svg>
  </div>

</template>

<style scoped>
.hackathon-banner-title {
  color: #2A2A2A;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
}

svg {
  margin-left: 6px;
}

</style>