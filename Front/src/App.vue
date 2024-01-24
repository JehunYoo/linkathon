<script setup lang="ts">

import HeaderNav from "@/components/Header/HeaderNav.vue";
import Footer from "@/components/Footer/Footer.vue";
</script>

<template>
  <HeaderNav/>
  <div class="content-margin">
    <div class="recruiting-content-container">
      <div class="content">
        <router-view/>
      </div>
    </div>
    <Footer/>
  </div>
</template>

<style scoped>
.content-margin {
  margin-top: 80px;
  display: flex;
  flex-direction: column;
}

.recruiting-content-container {
  width: 100vw;
  display: flex;
  justify-content: center;
}

.content {
  max-width: 1355px;
  width: 100%;
  padding: 20px;
}

</style>
