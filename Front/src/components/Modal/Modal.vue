<script lang="ts" setup>

import {onMounted, onUnmounted} from 'vue';

const lockScroll = () => {
  document.body.style.overflow = 'hidden';
};

const unlockScroll = () => {
  document.body.style.overflow = '';
};

onMounted(() => {
  lockScroll();
});

onUnmounted(() => {
  unlockScroll();
});

const emit = defineEmits(['closeModal']);
const closeModal = (num: number) => {
  emit('closeModal', num);
}
</script>

<template>
  <div class="blur" @click="closeModal(0)"/>
  <div class="slot">
    <svg fill="none" height="24" viewBox="0 0 26 24" width="26" xmlns="http://www.w3.org/2000/svg"
         @click="closeModal(0)">
      <line stroke="black" stroke-width="2" transform="matrix(-0.703573 -0.710623 -0.703573 0.710623 23.7803 23.998)"
            x2="30.9566"
            y1="-1" y2="-1"/>
      <line stroke="black" stroke-width="2" transform="matrix(-0.703573 0.710623 0.703573 0.710623 24 2.00098)"
            x2="30.9566"
            y1="-1" y2="-1"/>
    </svg>
    <slot>
    </slot>
  </div>
</template>

<style scoped>
* {
  -ms-overflow-style: none;
}

::-webkit-scrollbar {
  display: none;
}

svg {
  position: absolute;
  top: 24px;
  right: 24px;
}

.blur {
  z-index: 3;
  position: fixed;
  left: 50%;
  top: 50%;
  width: 100vw;
  height: 1000vh;
  background: rgba(255, 255, 255, 0.70);
  backdrop-filter: blur(5px);
  transform: translate(-50%, -50%)
}

.slot {
  max-height: 80%;
  width: max-content;
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  z-index: 9999;
  border-radius: 30px;
  border: 2px solid #7D3BFF;
  background: #FFF;
  overflow: scroll;
}

@media screen and (max-width: 698px) {
  .slot {
    min-width: 0;
    max-width: 100vw;
    width: 100vw;
    height: 100vh;
    max-height: 100vh;
    border: none;
    border-radius: 0;
    z-index: 9999999;
  }
}
</style>