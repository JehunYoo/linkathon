<script setup lang="ts">

import ThickDonutChart from "@/components/Chart/ThickDonutChart.vue";
import {Builder} from "builder-pattern";
import {PropType, ref, Ref} from "vue";
import {ProjectService} from "@/api/ProjectService.ts";

// const dummy = Builder<PerformanceChartDTO>().actualValue(80)
//     .centerText("80")
//     .label("Performance")
//     .color("#FFAA35").build()

// const dummyChanges = Builder<GitStatusDTO>()
//     .commits(288)
//     .insertions(7721)
//     .deletions(4617)
//     .name("홍길똥").build();

const props = defineProps({
  gitStatus : {
    type : Object as PropType<GitStatusDTO[]>,
    required : true
  },
  totalCommits : {
    type : Number,
    required : true
  }
});
console.log("p",props.gitStatus)
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
        <tr v-for="data in props.gitStatus" class="sub">
          <th>{{ data.userName }}</th>
          <th>{{ data.commits }}</th>
          <th>{{ data.insertions }}</th>
          <th>{{ data.deletions }}</th>
          <th>{{ ((data.commits / props.totalCommits) * 100).toFixed(1) }}%</th>
        </tr>
      </table>
    </div>
    <div class="chart">
      <ThickDonutChart :pc="props.gitStatus" :total="props.totalCommits"/>
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