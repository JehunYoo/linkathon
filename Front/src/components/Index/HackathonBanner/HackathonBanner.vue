<script setup lang="ts">
import HackathonBannerList from "@/components/Index/HackathonBanner/HackathonList.vue";
import IndexSubMenu from "@/components/Index/IndexSubMenu.vue";
</script>

<template>
  <div class="recruiting-container">
    <IndexSubMenu title="모집중인 해커톤"/>
    <HackathonBannerList/>
  </div>
</template>

<style scoped>
.recruiting-container {
  margin-top: 543px;
}
</style>