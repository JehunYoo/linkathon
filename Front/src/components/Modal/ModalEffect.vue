<script lang="ts" setup>
defineProps({
  text: {
    type: String,
    required: true
  }
})
</script>

<template>
  <div style="z-index:99999999; position: absolute; min-width: 164px; width:max-content; padding-left:13px; padding-right:13px;height: 60px; background: #7D3BFF; border-radius: 0 0 30px 0; text-align: center; line-height: 60px;color: #E9E9E9;
          font-size: 20px;
          font-style: normal;
          font-weight: 600;">
    {{ text }}
  </div>
</template>

<style scoped>

</style>