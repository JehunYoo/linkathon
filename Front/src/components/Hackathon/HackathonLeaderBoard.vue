<script lang="ts" setup>

import Pagination from "@/components/Pagination.vue";
</script>

<template>
  <h1>리더보드</h1>
  <table>
    <tr>
      <th>#</th>
      <th>팀명</th>
      <th>팀 멤버</th>
      <th>점수</th>
      <th>제출 수</th>
      <th>최종 테스트일</th>
    </tr>
    <tr class="high-right">
      <td>1</td>
      <td>홍길동길동홍</td>
      <td>주윤, 유제훈, 임종율, 마성진, 조민균, 김지민</td>
      <td>98점</td>
      <td>102회</td>
      <td>2024. 1. 18</td>
    </tr>
    <tr v-for="_ in 9">
      <td>1</td>
      <td>홍길동길동홍</td>
      <td>주윤, 유제훈, 임종율, 마성진, 조민균, 김지민</td>
      <td>98점</td>
      <td>102회</td>
      <td>2024. 1. 18</td>
    </tr>
  </table>
  <Pagination style="margin-bottom: 60px"/>
</template>

<style scoped>
.high-right {
  background: rgba(125, 59, 255, 0.10);
}

h1 {
  color: #000;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px;
  margin-top: 56px;
  margin-bottom: 18px;
}

table {
  width: 100%;
  text-align: center;
}

th {
  color: #606060;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px;
  padding: 10px;
}

td {
  padding: 20px;
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px;
}

tr, table, th, td {
  border-top: #909090 solid 1px;
  border-bottom: #909090 solid 1px;
}
</style>