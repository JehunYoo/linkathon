<script setup lang="ts">

import UserCard from "@/components/User/UserCard.vue";
import {ref} from "vue";

const as = ref<Boolean>(false);
const clicked = (bool: boolean) => {
  as.value = bool;
}
</script>

<template>
  <h1>스케줄 관리</h1>
  <h2>미팅 가능 일정</h2>
  <div class="schedule-menu">
    <div class="as">
      <div :class="!as?'select':'non-select'" @click="clicked(false)">오전</div>
      <div :class="as?'select':'non-select'" @click="clicked(true)">오후</div>
    </div>
    <div class="edit">수정하기</div>
  </div>
  <div class="time-container" v-if="!as">
    <div class="time-button" v-for="i in 12">{{ i - 1 }}:00</div>
  </div>
  <div class="time-container" v-if="as">
    <div class="time-button" v-for="i in 12">{{ i + 11 }}:00</div>
  </div>
  <h2>미팅 스케줄</h2>
  <h3>2024.01.19</h3>
  <div class="user-card-container">
    <div v-for="_ in 3" style="flex: 1;">
      <UserCard/>
    </div>
  </div>
  <h3>2024.01.20</h3>
  <div class="user-card-container">
    <div v-for="_ in 3" style="flex: 1;">
      <UserCard/>
    </div>
  </div>
</template>

<style scoped>
.edit {
  width: 70px;
  height: 30px;
  flex-shrink: 0;
  border-radius: 10px;
  background: #7D3BFF;
  color: #FFF;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
  line-height: 29px;
  border: #7D3CFF solid 1px;
  transition: color 0.3s ease;
}

.edit:hover {
  background: white;
  color: #7D3BFF;
}


.schedule-menu {
  max-width: 480px;
  display: flex;

}

.time-container {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
  max-width: 500px;
  margin-top: 12px;
  margin-bottom: 20px;
}

.time-button {
  width: 70px;
  height: 40px;
  border-radius: 10px;
  border: 1px solid #DEDEDE;
  color: #303030;
  text-align: center;
  font-size: 16px;
  font-style: normal;
  font-weight: 600;
  line-height: 39px;
}

.select, .non-select {
  width: 76px;
  padding-bottom: 8px;
  text-align: center;
}

.select {
  border-bottom: #7D3BFF solid 2px;
  color: #303030;
}

.non-select {
  border-bottom: #DEDEDE solid 2px;
  color: #606060;
}

.as {
  flex: 1;
  display: flex;
}

h1 {
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 28px;
}

h2 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 16px;
}

h3 {
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 500;
  line-height: 22.4px; /* 160% */
  border-bottom: 1px #7D3BFF solid;
  padding-bottom: 2px;
  width: max-content;
  margin-bottom: 12px;
}

.user-card-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}
</style>
