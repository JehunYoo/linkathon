<script lang="ts" setup>
import {PropType, ref, watchEffect} from 'vue';
import {ArcElement, Chart, DoughnutController, Legend, Tooltip} from 'chart.js';
Chart.register(DoughnutController, ArcElement, Tooltip, Legend);

const donutChart = ref<null | HTMLCanvasElement>(null);
const props = defineProps({
  pc: {
    type: Object as PropType<GitStatusDTO[]>,
    required:true
  },
  total : {
    type: Number,
    required:true
  }
});
watchEffect(() => {
  if (props.pc && props.pc.length > 0 && props.total) {
    renderChart();
  }
});
function renderChart() {
  if (!props.pc || props.pc.length === 0 || !props.total) {
    console.error('Invalid props provided!');
    return;
  }
    const data = {
    labels: props.pc.map(data => data?.userName),
    datasets: [{
      data: props.pc.map(data => ((data?.commits / props?.total)*100).toFixed(1)),
      backgroundColor: [
        'lightblue',
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
}

</script>
<template>
  <canvas ref="donutChart"></canvas>
</template>

<style scoped>

</style>
