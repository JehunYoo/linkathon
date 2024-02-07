<script lang="ts" setup>
import {inject, onMounted, onUnmounted, PropType, ref} from 'vue';

const updateSelect: Function = <Function>inject('selectedMenuField');
defineProps({
  menuList: {
    type: Object as PropType<String[]>
  }
})
const dropdownOpen = ref(false);
const select = ref(0);
const toggleDropdown = () => {
  dropdownOpen.value = !dropdownOpen.value;
};

const clickDropdownMenu = (item: number) => {
  select.value = item;
  updateSelect(item);
  dropdownOpen.value = false;
};

const handleClickOutside = (event: MouseEvent) => {
  const dropdownContainer = document.querySelector('.click-out2');
  if (dropdownContainer && !dropdownContainer.contains(event.target as Node)) {
    dropdownOpen.value = false;
  }
};

// 컴포넌트가 마운트된 후에 클릭 이벤트 리스너를 추가
onMounted(() => {
  window.addEventListener('click', handleClickOutside);
});

// 컴포넌트가 언마운트될 때 클릭 이벤트 리스너를 제거 (메모리 누수 방지)
onUnmounted(() => {
  window.removeEventListener('click', handleClickOutside);
});

</script>

<template>
  <div class="dropdown-container click-out2" @click="toggleDropdown">
    <div class="dropdown-box" v-if="menuList">
      {{ menuList[select] }}
      <svg fill="none" height="6" style="margin-left: 11px" viewBox="0 0 12 6" width="12"
           xmlns="http://www.w3.org/2000/svg">
        <path d="M1 1L6 5L11 1" stroke="#303030" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
      </svg>
    </div>
    <div v-if="dropdownOpen" class="dropdown-content">
      <div v-for="(item, index) in menuList" :key="index" @click="clickDropdownMenu(index)">{{ item }}</div>
    </div>
  </div>
</template>

<style scoped>
.dropdown-container {
  display: inline-block;
  position: relative;
  width: max-content;
}

.dropdown-box {
  color: #606060;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  padding: 9px 15px 10px;
  border-radius: 18px;
  border: 1px solid #606060;
  width: max-content;
}

.dropdown-content {
  color: #606060;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  padding: 9px 15px 10px;
  border-radius: 18px;
  border: 1px solid #606060;
  width: 100%;
  position: absolute;
  min-width: max-content;
  z-index: 2;
  background: white;
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
