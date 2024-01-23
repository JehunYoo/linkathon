<script setup lang="ts">

import FooterLeftContent from "@/components/Footer/FooterLeftContent.vue";
import FooterRightContent from "@/components/Footer/FooterRightContent.vue";
</script>

<template>
  <div class="footer-container">
    <footer>
      <FooterLeftContent/>
      <FooterRightContent/>
    </footer>
  </div>
</template>

<style scoped>
@media (max-width: 768px) {
  footer {
    flex-direction: column-reverse;
    border: none;
  }
}

.footer-container {
  z-index: 9999;
  display: flex;
  justify-content: center;
  width: 100vw;
  min-height: max-content;
  background: #F8F9FA;
}

footer {
  padding: 20px;
  max-width: 1355px;
  width: 100%;
  display: flex;
  align-items: center;
  border-top: #ECEEF0 0.5px solid;
}

</style>