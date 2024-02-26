<script lang="ts" setup>
import {ref} from 'vue';
import Store from "@/store";

const dummy = ref(["선택", "프론트엔드", "백엔드", "풀스택", "디자이너", "관리"]);
const backDummy = ref(["", "FRONTEND", "BACKEND", "FULLSTACK", "DESIGN", "MANAGE"]);
const dropdownOpen = ref(false);
const select = ref(0);

const toggleDropdown = () => {
  dropdownOpen.value = !dropdownOpen.value;
  Store.commit('setField', backDummy.value[select.value]);
};

const clickDropdownMenu = (item: number) => {
  select.value = item;
};

</script>

<template>
  <div class="dropdown-container" @click="toggleDropdown">
    <div class="dropdown-box">
      <div style="flex: 9; text-align: left">{{ dummy[select] }}</div>
      <svg fill="none" height="6" style="margin:auto" viewBox="0 0 12 6" width="12"
           xmlns="http://www.w3.org/2000/svg">
        <path d="M1 1L6 5L11 1" stroke="#303030" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
      </svg>
    </div>
    <div v-if="dropdownOpen" class="dropdown-content">
      <div v-for="(item, index) in dummy" :key="index" @click="clickDropdownMenu(index)">{{ item }}</div>
    </div>
  </div>
</template>

<style scoped>
.dropdown-container {
  display: inline-block;
  position: relative;
  width: 100%;
}

.dropdown-box {
  color: #606060;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  padding: 9px 15px 10px;
  border-radius: 5px;
  border: 1px solid #303030;
  width: 100%;
  display: flex;
  height: 40px;
}

.dropdown-content {
  color: #606060;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  padding: 9px 18px 10px;
  border-radius: 5px;
  border: 1px solid #303030;
  width: 100%;
  position: absolute;
  min-width: max-content;
  z-index: 2;
  background: white;
  top: 0;
}

.dropdown-content div {
  text-align: center;
  padding: 8px;
  color: black;
  text-decoration: none;
  display: block;
}

.dropdown-content div:hover {
  background-color: #f1f1f1;
  border-radius: 4px;
}
</style>
