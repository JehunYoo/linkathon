<script setup lang="ts">
const props = defineProps({
  rating: Number,
  width: String,
  height: String,
  fontSize: String,
});
</script>

<template>
  <div class="tier-box"
       :class="{
         'bronze': rating < 100,
         'silver': rating >= 100 && rating < 200,
         'gold': rating >= 200 && rating < 300,
         'platinum': rating >= 300 && rating < 400,
         'diamond': rating >= 400}"
       :style="{
          width:width,
          height:height,
          fontSize:fontSize,
          lineHeight:height
       }"
  >
    {{ props.rating }}
  </div>
</template>

<style scoped>
.tier-box {
  color: white;
  text-align: center;
  border-radius: 4px;
}

.bronze {
  background: brown;
}

.silver {
  background: darkgrey;
}

.gold {
  background: #F4D35E;
}

.platinum {
  background: mediumpurple;
}

.diamond {
  background: darkslateblue;
}
</style>