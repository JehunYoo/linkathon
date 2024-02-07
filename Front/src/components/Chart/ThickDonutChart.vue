<script lang="ts" setup>
import {onMounted, PropType, ref} from 'vue';
import {ArcElement, Chart, DoughnutController, Legend, Tooltip} from 'chart.js';

Chart.register(DoughnutController, ArcElement, Tooltip, Legend);

const donutChart = ref(null);
const props = defineProps({
  pc: {
    type: Object as PropType<GitStatusDTO[]>,
    required: true
  }
});

onMounted(() => {
  const value = props.pc?.map((data) => data.commits);
  const label = props.pc?.map((data) => data.name);
  const data = {
    labels: label,
    datasets: [{
      data: value, // 데이터 값과 일치하는 라벨들
      backgroundColor: [
        'cadetblue',
        '#D9D9D9',
        '#404040',
        '#FF6161',
        '#483d8b',
        '#F4D35E',
        'crimson',
        'midnightblue',
        'lightcoral',
        'red'
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
