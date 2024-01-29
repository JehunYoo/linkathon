<script lang="ts" setup>
import {computed, onMounted, PropType, ref} from 'vue';
import {Chart, registerables} from 'chart.js';

const donutChart = ref(null);
const props = defineProps({
  pc: Object as PropType<PerformanceChartDTO>
});


const centerTextPlugin = {
  id: 'centerText',
  afterDraw(chart) {
    const ctx = chart.ctx;
    const {width, height} = chart;
    const centerText = chart.config._config.centerText;

    ctx.save();
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.font = '30px Jockey One';
    ctx.fillStyle = props.pc.color;

    ctx.fillText(centerText, width / 2, height / 2);
    ctx.restore();
  }
};

Chart.register(...registerables, centerTextPlugin);


const computedCenterText = computed(() => {
  return props.pc.centerText ?? props.pc.actualValue.toString();
});

onMounted(() => {
  const maxValue = 100;
  const actualValue = props.pc.actualValue;
  const remainingValue = maxValue - actualValue;

  const data = {
    datasets: [{
      data: [remainingValue, actualValue],
      backgroundColor: [
        '#D9D9D9',
        props.pc.color
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
    centerText: computedCenterText.value
  };

  new Chart(donutChart.value.getContext('2d'), config);
});
</script>

<template>
  <canvas ref="donutChart"></canvas>
</template>

<style scoped>

</style>
