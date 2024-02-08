<script setup lang="ts">
import {ref} from "vue";
import DatePicker from 'vue3-datepicker';
const as = ref<Boolean>(false);
const clicked = (bool: boolean) => {
  as.value = bool;
}

const selectedDate = ref(new Date());
</script>

<template>
  <div class="interview-container">
    <h1>면접예약</h1>
    <h2>날짜 선택</h2>
    <DatePicker v-model="selectedDate" style="width: 100%; height: 40px; font-size: 14px; padding-left: 10px; margin-bottom: 16px; font-family: Pretendard-Regular,serif"/>
    <h2>홍길동님 미팅 가능 시간</h2>
    <div class="schedule-menu">
      <div class="as">
        <div :class="!as?'select':'non-select'" @click="clicked(false)">오전</div>
        <div :class="as?'select':'non-select'" @click="clicked(true)">오후</div>
      </div>
    </div>
    <div class="time-container" v-if="!as">
      <div class="time-button" v-for="i in 11">{{ i - 1 }}:00</div>
    </div>
    <div class="time-container" v-if="as">
      <div class="time-button" v-for="i in 3">{{ i + 11 }}:00</div>
    </div>
  </div>
</template>


<style scoped>
* {
  text-align: left;
}

h1 {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 16px;
}

h2 {
  font-size: 16px;
  margin-bottom: 12px;
}

.interview-container {
  padding: 20px;
  height: 80vh;
  max-height: 430px;
}

.schedule-menu {
  width: 100%;
  display: flex;
}

.time-container {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
  max-width: 480px;
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
</style>
