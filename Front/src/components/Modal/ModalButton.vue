<script setup lang="ts">
const props = defineProps({
  buttonText:{
    type:String,
    require:true
  }
});
</script>

<template>
  <div class="button">
    {{ props.buttonText }}
  </div>
</template>

<style scoped>
.button {
  color: #F2F2F2;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  width: max-content;
  border-radius: 5px;
  background: #7D3BFF;
  padding: 9px 17px;
  border: solid #7D3BFF 1px;
  transition: background-color 0.3s ease;
}

.button:hover {
  background: white;
  color: #303030;
}
</style>