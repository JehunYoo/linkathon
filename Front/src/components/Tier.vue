<script lang="ts" setup>
const props = defineProps({
  rating: {
    type: Number,
    default: 0
  },
  width: String,
  height: String,
  fontSize: String,
  radius: {
    type: String,
    default: "4px"
  },
});

const tierName: string[] = [
  "B",
  "S",
  "G",
  "P",
  "D"
];


</script>

<template>
  <div :class="{
         'bronze': rating < 100,
         'silver': rating >= 100 && rating < 200,
         'gold': rating >= 200 && rating < 300,
         'platinum': rating >= 300 && rating < 400,
         'diamond': rating >= 400}"
       :style="{
          width:width,
          height:height,
          fontSize:fontSize,
          lineHeight:height,
          borderRadius:radius,
       }"
       class="tier-box"
  >
    {{ tierName[Math.floor((props.rating >= 400 ? 400 : props.rating) / 100)] }}
  </div>
</template>

<style scoped>
.tier-box {
  color: white;
  text-align: center;
  border-radius: 4px;
  font-family: "Jockey One", sans-serif;
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