<script lang="ts" setup>
import {PropType} from "vue";
import {HackathonInfoDTO} from "@/dto/hackathonInfoDTO.ts";

const props = defineProps({
  data : {
    type : Object as PropType<HackathonInfoDTO>,
    required: true
  },
  name : {
    type : String,
    required : true
  }
});

function formatDate(date: Date) {
  const format_date = new Date(date);
  const month = format_date.getMonth() + 1;
  const day = format_date.getDate();
  return `${month}.${day}`;
}

</script>

<template>
  <RouterLink :to="`/hackathonDetail?id=${props.data.hackathonId}`" class="card-container">
    <img :src="props.data?.hackathonImageUrl" alt="" class="img">
    <div style="width: 100%">
      <div style="display: flex; width: 100%; gap: 10px">
        <div style="flex: 8">
          <h1>
            {{props.data.hackathonName}}
          </h1>
        </div>
        <div class="status">
          {{ props.name}}
        </div>
      </div>

      <h2>
        {{ props.data.hackathonTopic }}
      </h2>
      <div style="display: flex; gap: 10px; flex-direction: row">
        <div class="text-container">
          <div>
            <h4>
              모집기간
            </h4>
            <h3>
              {{ formatDate(props.data.registerDate) }}~{{ formatDate(props.data.teamDeadlineDate) }}
            </h3>
          </div>
          <div>
            <h4>
              진행기간
            </h4>
            <h3>
              {{ formatDate(props.data.startDate) }}~{{ formatDate(props.data.endDate) }}
            </h3>
          </div>
          <div>
            <h4>
              결과발표
            </h4>
            <h3>
              {{ formatDate(props.data.endDate) }}
            </h3>
          </div>
        </div>
      </div>

    </div>

  </RouterLink>
</template>

<style scoped>
.t {
  color: #303030;
  text-align: right;
  font-size: 16px;
  font-style: normal;
  font-weight: 600;
  margin-top: 28px;
}

@media screen and (max-width: 768px) {
  .status, .img, .t {
    display: none;
  }
}

.status {
  color: #303030;
  text-align: right;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 25px;
}

h4 {
  color: #303030;
  font-size: 12px;
  font-style: normal;
  font-weight: 500;
  line-height: 25px; /* 208.333% */
  letter-spacing: 0.72px;
}

h3 {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: 25px; /* 156.25% */
}

.text-container {
  display: flex;
  gap: 20px;
  flex: 1;
}

h1 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 700;
  line-height: 20px;
}

h2 {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 25px;
  margin-top: 6px;
}

.card-container {
  gap: 12px;
  padding: 12px;
  flex: 1;
  height: max-content;
  border-radius: 10px;
  border: 1px solid #DEDEDE;
  transition: transform 0.3s ease;
  display: flex;
}

.card-container:hover {
  transform: scale(1.01);
}

img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 5px;
}
</style>