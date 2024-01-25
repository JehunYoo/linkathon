<script lang="ts" setup>
import HeaderSideMenu from "@/components/Header/HeaderSideMenu.vue";
import {ref} from "vue";

const sidebarControl = ref<Number>(0);

const sidebarController = (num: number) => {
  sidebarControl.value = num;
}
</script>

<template>
  <HeaderSideMenu v-if="sidebarControl===1" :sidebar-controller="sidebarController"/>
  <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,600,0,0"
        rel="stylesheet"/>
  <div class="header-nav-container">
    <div class="header-nav">
      <router-link class="logo" to="/">LINK.</router-link>
      <div class="left-menu-container">
        <router-link class="header-nav-menu-hover" to="/recruit">팀원 구하기</router-link>
        <div class="header-nav-menu-hover">내 프로젝트</div>
        <div class="header-nav-menu-hover">해커톤</div>
        <router-link class="header-nav-menu-hover" to="/sharedProject">공유 프로젝트</router-link>
      </div>
      <div class="right-menu-container">
        <div class="hamburger-button">
          <span class="material-symbols-outlined" @click="sidebarController(1)">
            menu
          </span>
        </div>
        <router-link class="tl header-nav-menu-hover" to="/login">로그인</router-link>
        <div class="right-menu-divider">|</div>
        <router-link class="tr header-nav-menu-hover" to="/register">회원가입</router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
*:visited {
  color: #2B2B2B;
}

a {
  color: #2b2b2b;
}

.hamburger-button {
  color: #2B2B2B;
  display: none;
  font-weight: 400;
  font-size: 18px;
  line-height: 22px;
  font-style: normal;
  padding: 10px;
}

.hamburger-button:hover {
  color: #7D3BFF;
  font-weight: 700;
}

@media screen and (max-width: 1024px) {
  .left-menu-container, .right-menu-divider, .header-nav-menu-hover {
    display: none;
  }

  .hamburger-button {
    display: flex;
  }
}

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
  border-bottom: #7D3BFF solid 1px;
  position: fixed;
  background: white;
  top: 0;
  z-index: 5;
  padding-right: 10px;
  padding-left: 10px;
}
</style>