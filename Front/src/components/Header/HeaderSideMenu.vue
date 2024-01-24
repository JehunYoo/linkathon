<script setup lang="ts">

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

const props = defineProps({
  sidebarController: {
    type: Function,
    required: true
  }
})

</script>

<template>
  <div class="blur" @click="props.sidebarController(0)"/>
  <div class="side-bar">
    <svg xmlns="http://www.w3.org/2000/svg" width="19" height="18" viewBox="0 0 26 24" fill="none"
         @click="props.sidebarController(0)">
      <line y1="-1" x2="30.9566" y2="-1" transform="matrix(-0.703573 -0.710623 -0.703573 0.710623 23.7803 23.998)"
            stroke="black" stroke-width="2"/>
      <line y1="-1" x2="30.9566" y2="-1" transform="matrix(-0.703573 0.710623 0.703573 0.710623 24 2.00098)"
            stroke="black" stroke-width="2"/>
    </svg>
    <h1>
      MENU
    </h1>
    <div class="hr"/>
    <div class="side-bar-menu">
      <router-link to="/">홈으로</router-link>
      <router-link to="/recruit">팀원구하기</router-link>
      <div>내 프로젝트</div>
      <div>해커톤</div>
      <div>명예의 전당</div>
      <div>마이페이지</div>
      <router-link  to="/login">로그인</router-link>
      <div>회원가입</div>
    </div>
  </div>
</template>

<style scoped>
*:visited {
  color: #404040;
}

a {
  color: #404040;
}

* {
  box-shadow: none;
}

svg {
  position: absolute;
  right: 33px;
  top: 35px;
}

h1 {
  font-family: "Jockey One", sans-serif;
  font-size: 26px;
  line-height: 28px;
  font-weight: lighter;
  color: #303030;
}

* {
  -ms-overflow-style: none;
}

.side-bar-menu {
  gap: 28px;
  display: flex;
  flex-direction: column;
  height: 100%;
  overflow: scroll;
  color: #404040;
}

.side-bar-menu div,a {
  transition: color 0.3s ease, margin-left 0.3s ease, transform 0.3s ease;
}

.side-bar-menu div:hover, a:hover {
  color: #7D3CFF;
  margin-left: 10px;
  transform: scale(1.05);
}

::-webkit-scrollbar {
  display: none;
}

.hr {
  border-bottom: #7D3CFF 1px solid;
  width: 100%;
  margin-top: 1px;
  margin-bottom: 20px;
}

.blur {
  z-index: 1000;
  position: fixed;
  left: 50%;
  top: 50%;
  width: 100vw;
  height: 1000vh;
  background: rgba(255, 255, 255, 0.70);
  backdrop-filter: blur(5px);
  transform: translate(-50%, -50%)
}

.side-bar {
  padding: 30px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  position: fixed;
  z-index: 99999;
  right: 0;
  top: 0;
  width: 260px;
  background: white;
  border-left: #7D3CFF solid 1px;
  height: 100vh;
}
</style>