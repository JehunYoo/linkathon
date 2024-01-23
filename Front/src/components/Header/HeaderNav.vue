<script setup lang="ts">

</script>

<template>
  <div class="header-nav-container">
    <div class="header-nav">
      <div class="logo">LINK.</div>
      <div class="left-menu-container">
        <div class="header-nav-menu-hover">팀원 구하기</div>
        <div class="header-nav-menu-hover">내 프로젝트</div>
        <div class="header-nav-menu-hover">해커톤</div>
        <div class="header-nav-menu-hover">공유 프로젝트</div>
      </div>
      <div class="right-menu-container">
        <div class="tl header-nav-menu-hover">로그인</div>
        <div class="right-menu-divider">|</div>
        <div class="tr header-nav-menu-hover">회원가입</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.header-nav-menu-hover:hover {
  color: #7D3BFF;
  font-weight: 700;
}

.tr {
  text-align: right;
}

.tl {
  text-align: left;
}

.logo {
  flex: 1;
  max-width: max-content;
  font-family: "Jockey One", sans-serif;
  font-size: 36px;
  color: #7D3BFF;
}

.right-menu-divider {
  max-width: max-content;
}

.right-menu-container {
  gap: 20px;
  display: flex;
  width: max-content;
  justify-content: right;
}

.right-menu-container div {
  flex: 1;
  min-width: max-content;
}

.left-menu-container {
  color: #2B2B2B;
  font-weight: 400;
  font-size: 18px;
  line-height: 22px;
  font-style: normal;
  display: flex;
  gap: 90px;
  flex: 5;
}

.left-menu-container div {
  min-width: max-content;
}

.header-nav {
  display: flex;
  padding: 20px;
  max-width: 1355px;
  width: 100vw;
  gap: 88px;
  align-items: center;
  justify-content: center;
}

.header-nav-container {
  display: flex;
  justify-content: center;
  width: 100vw;
  height: 80px;
  border-bottom: #7D3BFF solid 0.5px;
  position: fixed;
  background: white;
  top: 0;
  z-index: 999;
}
</style>