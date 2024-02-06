<script lang="ts" setup>
import {computed, onMounted, PropType, ref} from 'vue';
import {Chart, registerables} from 'chart.js';

const donutChart = ref(null);
const props = defineProps({
  pc: {
    type: Object as PropType<PerformanceChartDTO>,
    required: true
  },
});

function getScoreColor(score: number): string {
  if (score >= 90) {
    return "#388E3C"; // 진한 녹색
  } else if (score >= 70) {
    return "#1976D2"; // 진한 파란색
  } else if (score >= 50) {
    return "#FBC02D"; // 진한 노랑색
  } else {
    return "#D32F2F"; // 진한 빨강색
  }
}

onMounted(() => {
  Chart.register(...registerables);
  const color = getScoreColor(props.pc?.actualValue);
  const computedCenterText = computed(() => {
    return props.pc.centerText ?? props.pc.actualValue.toString();
  });
  const maxValue = 100;
  const actualValue = props.pc.actualValue;
  const remainingValue = maxValue - actualValue;
  const data = {
    datasets: [{
      data: [remainingValue, actualValue],
      backgroundColor: [
        '#D9D9D9',
        color
      ]
    }]
  };

  const config = {
    type: 'doughnut',
    data,
    options: {
      responsive: true,
      maintainAspectRatio: false,
      cutout: '85%',
      plugins: {
        legend: {
          display: false
        }
      }
    },
    centerText: computedCenterText.value,
  };
  //@ts-ignore
  new Chart(donutChart.value.getContext('2d'), config);
});
</script>

<template>
  <div class="center-text" :style="{'color':getScoreColor(props.pc?.actualValue)}">{{ pc.centerText }}</div>
  <canvas ref="donutChart"/>
</template>

<style scoped>
.center-text {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  font-family: "Jockey One", sans-serif;
  font-size: 30px;
}
</style>
