<script setup lang="ts">

import ThickDonutChart from "@/components/Chart/ThickDonutChart.vue";
import {Builder} from "builder-pattern";

const dummy = Builder<PerformanceChartDTO>().actualValue(80)
    .centerText("80")
    .label("Performance")
    .color("#FFAA35").build()

const dummyChanges = Builder<GitStatusDTO>()
    .commits(288)
    .insertions(7721)
    .deletions(4617)
    .name("홍길똥").build();

const dummyList: GitStatusDTO[] = [];
for (let i = 0; i < 5; i++) {
  dummyList.push(dummyChanges);
}
</script>

<template>
  <h1>깃허브 분석</h1>
  <div class="git-container">
    <div class="list-container">
      <table>
        <tr class="title">
          <th>Author</th>
          <th>Commits</th>
          <th>Insertions</th>
          <th>Deletions</th>
          <th>Changes</th>
        </tr>
        <tr v-for="data in dummyList" class="sub">
          <th>{{ data.name }}</th>
          <th>{{ data.commits }}</th>
          <th>{{ data.insertions }}</th>
          <th>{{ data.deletions }}</th>
          <th>Changes</th>
        </tr>
      </table>
    </div>
    <div class="chart">
      <ThickDonutChart :pc="dummy"/>
    </div>
  </div>


</template>

<style scoped>
h1 {
  margin-top: 3px;
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 11px;
}

.sub {
  border-bottom: 1px solid #303030;
}

.title {
  border-bottom: solid 2px #303030;
}

th {
  line-height: 33px;
}

.git-container {
  padding: 20px;
  border-radius: 10px;
  border: solid 1px #7D3BFF;
  display: flex;
  width: 100%;
  height: max-content;
  gap: 13px;
}

.chart {
  width: 100%;
  max-width: 237px;
  max-height: 237px;
}

.list-container {
  flex: 1;
  display: flex;
  align-items: center;
}

table {
  width: 100%;
  height: max-content;
}

@media screen and (max-width: 1230px) {
  .git-container {
    flex-direction: column-reverse;
  }
  .chart {
    width: 95%;
    max-width: 100%;
  }
}
</style>