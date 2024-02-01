<script setup lang="ts">
import {PropType} from "vue";

const props = defineProps({
  data: {
    type: Object as PropType<SimpleUserDTO>,
    required: true
  }
})
</script>

<template>
  <div class="simple-container">
    <img :src="props.data?.userImgUrl" alt="">
    <div style="height:50px; display: flex">
      <div style="margin: auto">
        <h1>{{ props.data.userName }}</h1>
        <h2>{{ props.data.introduce }}</h2>
      </div>
    </div>
  </div>
</template>

<style scoped>

h1 {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px; /* 140% */
}

h2 {
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: 22.4px; /* 160% */
}
.simple-container {
  padding: 8px 0px;
  display: flex;
  gap: 12px;
}

img {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 15px;
}
</style>