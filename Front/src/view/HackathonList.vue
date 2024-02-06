<script lang="ts" setup>

import Pagination from "@/components/Pagination.vue";
import HackathonCard from "@/components/Hackathon/HackathonCard.vue";
import {ref} from "vue";

const categorySelect = new Set<number>();
const refSelect = ref(categorySelect);
const click = (num: number) => {
  if (refSelect.value.has(num))
    refSelect.value.delete(num);
  else
    refSelect.value.add(num);
}

const listName:String[] = ["모집중", "모집전", "진행중", "종료됨"];
</script>

<template>
  <h1>도전으로 기회를 얻으세요</h1>
  <h2>안녕하세요. 백엔드 개발자입니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다. 동해번쩍 서해번쩍 잘합니다.</h2>
  <div class="button">해커톤 참여방법 알아보기</div>
  <hr>
  <div class="category-button-container">
    <template v-for="(name, i) in listName">
      <div class="category-button" :class="{'select':refSelect.has(i)}" @click="click(i)">{{name}}</div>
    </template>
  </div>
  <div class="container">
    <template v-for="_ in 6">
      <HackathonCard/>
    </template>
  </div>

  <Pagination style="margin-bottom: 60px"/>
</template>

<style scoped>
.select {
  background: #7d3bff;
  color: white;
}

.container {
  margin-top: 12px;
  display: flex;
  flex-direction: column;
  width: 100%;
  height: max-content;
  gap: 12px;
}

.category-button-container {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
}

.category-button {
  border-radius: 18px;
  border: 1px solid #606060;
  padding: 8px 12px;
  width: max-content;
  transition: color 0.3s ease;
}

hr {
  background: #000;
  margin-top: 48px;
  margin-bottom: 20px;
}

.button {
  max-width: 295px;
  height: 40px;
  border-radius: 10px;
  background: #7D3BFF;
  color: #F2F2F2;
  text-align: center;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: 39px;
  border: solid 1px #7D3BFF;
  transition: color 0.3s ease;
}

h1 {
  margin-top: 48px;
  color: #000;
  font-size: 32px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 20px;
}

h2 {
  max-width: 650px;
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: 22px;
  margin-bottom: 20px;
}

.button:hover {
  background: white;
  color: #7D3BFF;
}
</style>