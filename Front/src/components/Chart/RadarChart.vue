<script setup lang="ts">
import {onMounted, PropType, ref} from 'vue';
import {Chart, registerables} from 'chart.js';
import {AuditResult} from "@/Interface/frontReport.ts";

Chart.register(...registerables);

const radarChartRef = ref<HTMLCanvasElement | null>(null);

const props = defineProps({
  report: {
    type: Object as PropType<AuditResult>,
    required: true
  },
  url: {
    type: String,
    required: true
  }
});

onMounted(() => {
  if (radarChartRef.value) {
    const keys: string[] = Object.keys(props.report);
    const data: number[] = [];
    for (let key in keys) {
      data.push(props.report[keys[key]].score * 100);
    }
    //@ts-ignore
    const ctx = radarChartRef.value.getContext('2d');
    //@ts-ignore
    new Chart(ctx, {
      type: 'radar',
      data: {
        labels: keys,
        datasets: [{
          data: data,
          fill: true,
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgb(255, 99, 132)',
          pointBackgroundColor: 'rgb(255, 99, 132)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgb(255, 99, 132)'
        }]
      },
      options: {
        plugins: {
          legend: {
            display: false // 범례를 숨김
          }
        },
        elements: {
          line: {
            borderWidth: 3
          }
        },
        scales: {
          r: {
            suggestedMin: 0, // 최소 눈금값을 0으로 설정
            suggestedMax: 100,
            pointLabels: {
              font: {
                size: 14
              }
            },
            ticks: {
              stepSize: 25, // 눈금 간격 설정 (옵션)
              font: {
                size: 12 // 눈금 레이블 폰트 사이즈 조절
              }
            }
          }
        }
      },
    });
  }
});
</script>

<template>
  <div class="chart-container-box">
    <div class="chart-container">
      <canvas ref="radarChartRef"></canvas>
    </div>
  </div>

</template>

<style scoped>
.chart-container-box {
  display: flex;
  justify-content: center;
}
.chart-container {
  height: 39vh;
  width: 39vh;
  margin-bottom: -30px;
}
</style>