<script setup lang="ts">

import {onMounted, Ref, ref} from "vue";
import Modal from "@/components/Modal/Modal.vue";
import BarChart from "@/components/Chart/BarChart.vue";
import {ProjectService} from "@/api/ProjectService.ts";
import {PageableBackPerformance} from "@/dto/projectDTO.ts";
import ThinDonutChart from "@/components/Chart/ThinDonutChart.vue";
import {Builder} from "builder-pattern";
import {BackPerformanceMessageResponseDto} from "@/dto/BackPerformanceMessageResponseDto.ts";

defineProps({
  editable: {
    type: Boolean,
    default: true
  },
});

const projectService = new ProjectService();
let refReport: Ref<PageableBackPerformance> = ref({} as PageableBackPerformance);
const refMessageCount: Ref<BackPerformanceMessageResponseDto> = ref({} as BackPerformanceMessageResponseDto);

onMounted(async () => {
  await getBackendReport();
})

const getBackendReport = async () => {
  refReport.value = await projectService.getBackMetrics(1);
  console.log(refReport)
  refMessageCount.value = await projectService.getBackMetricsMessageCounts(1);
}

const modalController = ref<boolean>(false);

function modalSwitch() {
  modalController.value = !modalController.value
  detail.value = -1;
}

const detail = ref<number>(-1);

function calculateGrade(score: number): string {
  if (score >= 90) {
    return 'A';
  } else if (score >= 80) {
    return 'B';
  } else if (score >= 70) {
    return 'C';
  } else if (score >= 60) {
    return 'D';
  } else {
    return 'E';
  }
}

function calculateSecurityGrade(score: number): string {
  if (score >= 500) {
    return 'E';
  } else if (score >= 400) {
    return 'D';
  } else if (score >= 300) {
    return 'C';
  } else if (score >= 200) {
    return 'B';
  } else {
    return 'A';
  }
}
</script>

<template>
  <Modal v-if="modalController" @closeModal="modalSwitch">
    <div class="modal-container">
      <BarChart :data="[refMessageCount.addCount, refMessageCount.removeCount, refMessageCount.makeCount, refMessageCount.completeCount
      ,refMessageCount.changeCount, refMessageCount.mergeCount, refMessageCount.refactorCount, refMessageCount.moveCount, refMessageCount.replaceCount, refMessageCount.etcCount]"/>
      <h1 style="">개선 사항</h1>
      <div class="grid">
        <h1 style="text-align: left; margin-bottom: 3px; margin-top: 3px; font-family: Pretendard-Regular,serif">
          수정 메시지 예시
        </h1>
        <h2 style="text-align: left; margin-top: 5px;flex: 1;"></h2>
      </div>
      <div class="content">
        <h3>• 추가</h3>
        <div class="modal-text">Add a private constructor to hide the implicit public one.<br>암시적으로 공개된 생성자를 숨기기 위해 private 생성자를 추가해주세요.</div>
        <h3>• 삭제</h3>
        <div class="modal-text">Remove this unused "example" private field.<br>사용되지 않는 "example" 필드를 삭제해주세요.</div>
        <h3>• 생성</h3>
        <div class="modal-text">Make this "public static url" field final<br>'public static url' 필드를 final로 만들어주세요.</div>
        <h3>• 완료</h3>
        <div class="modal-text">Complete the task associated to this TODO comment.<br>TODO 주석과 관련된 작업을 완료해주세요.</div>
        <h3>• 수정</h3>
        <div class="modal-text">Change the visibility of this constructor to "protected".<br>생성자의 접근제한자를 'protected'로 수정해주세요.</div>
        <h3>• 병합</h3>
        <div class="modal-text">Merge this if statement with the enclosing one.<br>이중 if문을 가능한 합쳐주세요.</div>
        <h3>• 리팩토링</h3>
        <div class="modal-text">Refactor this method to reduce its Cognitive Complexity.<br>코드의 복잡도를 줄이기 위해 메소드를 리팩토링해주세요.</div>
        <h3>• 이동</h3>
        <div class="modal-text">Move parameter "state" after parameters without default value.<br>기본값이 없는 매개변수 뒤에 매개변수 'state'를 따로 이동하십시오.</div>
        <h3>• 대체</h3>
        <div class="modal-text">Replace this use of System.out or System.err by a logger<br>System.out 또는 System.err의 사용을 로거로 대체해주세요.</div>
      </div>
    </div>
  </Modal>
  <h1>백엔드</h1>
  <section v-if="refReport">
    <h1 style="margin-bottom: 0;" v-if="refReport.pageable?.totalPages===0">분석된 데이터가 없습니다!</h1>
    <div class="chart-container">
      <template v-for="data in refReport.backMetrics">
        <div class="chart">
          <ThinDonutChart
              :pc="Builder<PerformanceChartDTO>().actualValue(data.bugs).centerText(calculateGrade(100-data.bugs)).build()"/>
          <h2>버그</h2>
        </div>
        <div class="chart">
          <ThinDonutChart
              :pc="Builder<PerformanceChartDTO>().actualValue(data.coverage).centerText(data.coverage*100+'%').build()"/>
          <h2>테스트 자동화</h2>
        </div>
        <div class="chart">
          <ThinDonutChart
              :pc="Builder<PerformanceChartDTO>().actualValue((data.codeSmells)/5).centerText(calculateGrade((500-data.codeSmells)/5)).build()"/>
          <h2>코드 악취</h2>
        </div>
        <div class="chart">
          <ThinDonutChart
              :pc="Builder<PerformanceChartDTO>().actualValue(data.duplications*100).centerText(data.duplications*100+'%').build()"/>
          <h2>코드 중복</h2>
        </div>
        <div class="chart">
          <ThinDonutChart
              :pc="Builder<PerformanceChartDTO>().actualValue(data.securityRating * 100).centerText(calculateSecurityGrade((data.securityRating * 100))).build()"/>
          <h2>보안</h2>
        </div>
      </template>
    </div>
    <h2>* 코드 품질과 보안을 개선하기 위한 분석 결과입니다.</h2>
  </section>
  <div class="button-container">
    <div class="button" @click="modalSwitch()" v-if="refReport.pageable?.totalPages!==0">분석 상세정보</div>
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
  min-height: calc(100% - 96px);
  max-height: max-content;
  display: flex;
  flex-direction: column;
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
  margin-bottom: 16px;
}

.chart-container {
  margin-bottom: 16px;
  display: flex;
  gap: 18px;
  flex-wrap: wrap;
  justify-content: center;
}
</style>