<script lang="ts" setup>

</script>

<template>
  <div class="footer-fit">
    <div class="content-fit">
      <slot/>
    </div>
  </div>
</template>

<style scoped>
.footer-fit {
  text-align: center;
  display: block;
  height: calc(100vh - 100px);
}

.content-fit {
  gap: 20px;
  display: flex;
  flex-direction: column;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>