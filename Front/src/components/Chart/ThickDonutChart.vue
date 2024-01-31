<script lang="ts" setup>
import {onMounted, PropType, ref} from 'vue';
import {Chart} from 'chart.js';

const donutChart = ref(null);
const props = defineProps({
  pc: {
    type: Object as PropType<PerformanceChartDTO>,
    required:true
  }
});

onMounted(() => {
  const actualValue = props.pc.actualValue;
  const data = {
    labels: ['Labe', 'Label2', 'Label3', 'Label4', 'Label5', 'Label6'],
    datasets: [{
      data: [actualValue, 20, 30, 40, 50, 60, 70, 80, 90], // 데이터 값과 일치하는 라벨들
      backgroundColor: [
        'cadetblue',
        '#D9D9D9',
        '#404040',
        '#FF6161',
        '#483d8b',
        '#F4D35E',
        'crimson',
        'midnightblue',
        'lightcoral'
      ],
    }]
  };
  const config = {
    type: 'doughnut',
    data,
    options: {
      responsive: true,
      maintainAspectRatio: false,
      cutout: '45%',
      plugins: {
        legend: {
          display: true,
          position: 'right',
          labels: {
            usePointStyle: true,
          }
        }
      }
    },
    centerText: ""
  };

  //@ts-ignore
  new Chart(donutChart.value.getContext('2d'), config);
});

</script>
<template>
  <canvas ref="donutChart"></canvas>
</template>

<style scoped>

</style>
