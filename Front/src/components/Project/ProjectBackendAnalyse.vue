<script setup lang="ts">

import {onMounted, Ref, ref} from "vue";
import Modal from "@/components/Modal/Modal.vue";
import BarChart from "@/components/Chart/BarChart.vue";
import {ProjectService} from "@/api/ProjectService.ts";
import {PageableBackPerformance} from "@/dto/projectDTO.ts";

defineProps({
  editable: {
    type: Boolean,
    default: true
  },
});

// const calculateAverageScores = (reports: Report[]) => {
//   const scoreSums = new Map<string, number>();
//   const scoreCounts = new Map<string, number>();
//
//   reports.forEach((report) => {
//     Object.keys(report.report).forEach((key) => {
//       const currentScore = report.report[key].score
//
//       if (scoreSums.has(key)) {
//         scoreSums.set(key, scoreSums.get(key) || currentScore);
//         scoreCounts.set(key, scoreCounts.get(key) || 1);
//       } else {
//         scoreSums.set(key, currentScore);
//         scoreCounts.set(key, 1);
//       }
//     });
//   });
//
//   const averageScores = new Map<string, number>();
//   scoreSums.forEach((sum, key) => {
//     averageScores.set(key, Math.round(sum / (scoreCounts.get(key) || 0) * 100));
//   });
//
//   return Array.from(averageScores);
// };

const projectService = new ProjectService();
let refReport: Ref<PageableBackPerformance> = ref({} as PageableBackPerformance);

onMounted(async () => {
  await getBackendReport();
  await projectService.getBackMetricsMessageCounts(1);
})

const getBackendReport = async () => {
  refReport.value = await projectService.getBackMetrics(1);
  console.log(refReport.value.backMetrics)
}

const updateBackendReport = () => {
  alert("요청이 완료되었습니다. 대기열에 따라 처리 시간이 변동되며 평균적으로 페이지 1개당 20~30초가 소모됩니다.")

  projectService.getBackMetrics(1);
}

// const buildObject = (score: number) => {
//   return Builder<PerformanceChartDTO>()
//       .centerText((score).toString())
//       .actualValue((score))
//       .build()
// }

const modalController = ref<boolean>(false);

function modalSwitch() {
  modalController.value = !modalController.value
  detail.value = -1;
}

const detail = ref<number>(-1);
</script>

<template>
  <Modal v-if="modalController" @closeModal="modalSwitch">
    <div class="modal-container">
      <BarChart/>
      <h1 style="">딱콩이 레포트 </h1>
      <div class="grid">
        <h1 style="text-align: left; margin-bottom: 3px; margin-top: 3px; font-family: Pretendard-Regular,serif">
          딱콩
        </h1>
        <h2 style="text-align: left; margin-top: 5px;flex: 1;">딱콩이</h2>
      </div>
      <div class="content">
        <h3>• 설명</h3>
        <div class="modal-text">딱콩딱콩</div>
      </div>
    </div>
  </Modal>
  <h1>백엔드</h1>
  <section>
    <h1 style="margin-bottom: 0">분석된 데이터가 없습니다!</h1>
    <div class="chart-container">
<!--      <template v-for="data in calculateAverageScores(refReport)">-->
      <template>
        <div>
<!--          <div class="chart">-->
<!--            <ThinDonutChart :pc="buildObject(data[1])"/>-->
<!--          </div>-->
<!--          <h2>{{ data[0] }}</h2>-->
        </div>
      </template>
    </div>
    <h2>* 코드 품질과 보안을 개선하기 위한 분석 결과입니다.</h2>
  </section>

  <div class="button-container">
    <div class="button" @click="updateBackendReport" v-if="editable">백엔드 분석 요청</div>
<!--    <div class="button" @click="modalSwitch()" v-if="refReport.backMetrics.length!==0">분석 상세정보</div>-->
  </div>

</template>

<style scoped>
.grid {
  border-bottom: #6f7070 solid 2px;
  padding: 10px;
  width: 100vw;
  max-width: 100%;
  display: flex;
  gap: 7px;
}

.content {
  margin-top: 20px;
  height: calc(30vh - 80px);
  overflow: scroll;
  overflow-x: hidden;
  padding: 10px;

}

@media screen and (max-width: 698px) {
  .content {
    height: calc(50vh - 80px);
  }
}

section {
  padding: 18px;
  border-radius: 10px;
  border: 1px solid #7D3BFF;
  background: #FFF;
  justify-content: center;
  min-height: calc(100% - 100px);
  max-height: max-content;
}

.modal-text {
  margin-left: 13px;
  margin-top: 8px;
  margin-bottom: 8px;
  line-height: 27px;
}

.modal-container {
  padding: 20px;
  max-width: 680px;
}

.button-container {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 14px;
  margin-bottom: 10px;
  gap: 14px;
}

.button {
  flex: 1;
  height: 34px;
  border-radius: 5px;
  background: #7d3bff;
  border: #7d3bff solid 1px;
  line-height: 33px;
  color: #FFF;
  text-align: center;
  font-size: 16px;
  font-style: normal;
  font-weight: lighter;
  transition: 0.3s ease color;
}

.button:hover {
  color: #7d3bff;
  background: white;
}

section {
  padding: 18px;
  border-radius: 10px;
  border: 1px solid #7D3BFF;
  background: #FFF;
  justify-content: center;
}

h1 {
  color: #303030;
  text-align: center;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  margin-bottom: 18px;
}

h2 {
  color: #303030;
  text-align: center;
  font-family: Pretendard-Regular, serif;
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
  line-height: 22px;
}

h3 {
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
}

@media screen and (max-width: 768px) {

  .chart-container {
    max-width: 100vw;
    width: 100%;
  }
}

.chart {
  width: 92px;
  height: 92px;
  position: relative;
}

.chart-container {
  margin-bottom: 16px;
  display: flex;
  gap: 18px;
  flex-wrap: wrap;
  justify-content: center;
}
</style>