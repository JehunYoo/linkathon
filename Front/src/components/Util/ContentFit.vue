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
  height: max-content;
  min-height: calc(100vh - 100px);
  display: flex;
  justify-content: center;
}

.content-fit {
  margin: auto;
  max-width: 476px;
  width: 80vw;
  display: flex;
  flex-direction: column;
  padding-bottom: 50px;
  padding-top: 50px;
}


</style>