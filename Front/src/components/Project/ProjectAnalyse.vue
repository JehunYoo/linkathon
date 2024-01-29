<script setup lang="ts">

import ThinDonutChart from "@/components/Chart/ThinDonutChart.vue";
import {Builder} from "builder-pattern";
import Pagination from "@/components/Pagination.vue";

const dummy = Builder<PerformanceChartDTO>().actualValue(80)
    .centerText("80")
    .label("Performance")
    .color("#FFAA35").build()

</script>

<template>
  <div class="chart-list">
    <div>
      <h1>Frontend</h1>
      <section>
        <div class="chart-container">
          <template v-for="_ in 5">
            <div>
              <div class="chart">
                <ThinDonutChart :pc="dummy"/>
              </div>
              <h2>Performance</h2>
            </div>
          </template>
        </div>
        <div class="button-container">
          <div class="button">Frontend Analysis</div>
        </div>

      </section>
      <Pagination page-name="fePage"/>
    </div>
    <div>
      <h1>Backend</h1>
      <section>
        <div class="chart-container">
          <template v-for="_ in 5">
            <div>
              <div class="chart">
                <ThinDonutChart :pc="dummy"/>
              </div>
              <h2>Performance</h2>
            </div>
          </template>
        </div>
        <div class="button-container">
          <div class="button">Backend Analysis</div>
        </div>

      </section>
      <Pagination page-name="bePage"/>
    </div>

  </div>
</template>

<style scoped>
@media screen and (max-width: 1304px) {
  .chart-list {
    flex-direction: column;
  }
}

.button-container {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 26px;
  margin-bottom: 10px;
}

.button {
  width: 130px;
  height: 34px;
  border-radius: 5px;
  background: #7D3BFF;
  border: #7D3CFF solid 1px;
  line-height: 33px;
  color: #FFF;
  text-align: center;
  font-family: "Jockey One", serif;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  transition: 0.3s ease color;
}

.button:hover {
  color: #7D3CFF;
  background: white;
}

section {
  padding: 18px;
  border-radius: 10px;
  border: 2px solid #7D3BFF;
  background: #FFF;
  justify-content: center;
}

h1 {
  color: #303030;
  text-align: center;
  font-family: "Jockey One", serif;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  margin-bottom: 18px;
}

h2 {
  color: #303030;
  text-align: center;
  font-family: "Jockey One", serif;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: 22px;
}

@media screen and (max-width: 768px) {
  .chart-list {
    flex-direction: column;
  }

  .chart-container {
    max-width: 100vw;
    width: 100%;
  }
}

.chart-list {
  display: flex;
  gap: 18px;
}

.chart {
  width: 92px;
  height: 92px;
}

.chart-container {
  display: flex;
  gap: 18px;
  flex-wrap: wrap;
  justify-content: center;
}
</style>