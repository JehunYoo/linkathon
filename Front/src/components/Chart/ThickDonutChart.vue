<script lang="ts" setup>
import {onMounted, PropType, ref} from 'vue';
import {Chart} from 'chart.js';

const donutChart = ref(null);
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
console.log(props.pc)
onMounted(() => {
  // const actualValue = props.pc.actualValue;
  const data = {
    // labels: ["username","commits","insertions","deletions","changes"],
    labels: props.pc.map(data => data.userName),
    datasets: [{
      // data: props.pc.map(data => data.commits),
      // backgroundColor: [
      //   'cadetblue',
      //   '#D9D9D9',
      //   '#404040',
      //   '#FF6161',
      //   '#483d8b',
      //   '#F4D35E',
      //   'crimson',
      //   'midnightblue',
      //   'lightcoral'
      // ]
      // data: props.pc.map(data => data.insertions),
      // backgroundColor: [
      //   'lightblue',
      //   '#D9D9D9',
      //   '#404040',
      //   '#FF6161',
      //   '#483d8b',
      //   '#F4D35E',
      //   'crimson',
      //   'midnightblue',
      //   'lightcoral'
      // ]},{
      // data: props.pc.map(data => data.deletions),
      // backgroundColor: [
      //   'lightblue',
      //   '#D9D9D9',
      //   '#404040',
      //   '#FF6161',
      //   '#483d8b',
      //   '#F4D35E',
      //   'crimson',
      //   'midnightblue',
      //   'lightcoral'
      // ]},{
      // data: props.pc.map(data => data.insertions),
      // backgroundColor: [
      //   'lightblue',
      //   '#D9D9D9',
      //   '#404040',
      //   '#FF6161',
      //   '#483d8b',
      //   '#F4D35E',
      //   'crimson',
      //   'midnightblue',
      //   'lightcoral'
      // ],
      data: props.pc.map(data => ((data.commits / props.total)*100).toFixed(1)),
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
});

</script>
<template>
  <canvas ref="donutChart"></canvas>
</template>

<style scoped>

</style>
