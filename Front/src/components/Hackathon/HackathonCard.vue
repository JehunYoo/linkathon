<script lang="ts" setup>
import {Builder} from "builder-pattern";

const dummy: HackathonInfoDetailDTO = Builder<HackathonInfoDetailDTO>()
    .flowStart(new Date())
    .flowEnd(new Date())
    .recruitmentStart(new Date())
    .recruitmentEnd(new Date())
    .announce(new Date())
    .subject("IT 교육 수강생 대상 서비스")
    .imgSrc("https://cdn.crowdpic.net/list-thumb/thumb_l_F25C5FD45B78842BE8B499E04852D8CB.jpg")
    .title(['제 1회 교육관련 웹 / 앱 서비스 제작 해커톤'])
    .status("참가 신청중")
    .count(100)
    .hackathonId(1)
    .build()

function formatDate(date: Date) {
  const month = date.getMonth() + 1;
  const day = date.getDate();
  return `${month}.${day}`;
}

</script>

<template>
  <RouterLink :to="`/hackathonDetail?id=${dummy.hackathonId}&mode=${0}`" class="card-container">
    <img :src="dummy.imgSrc" alt="" class="img">
    <div style="width: 100%">
      <div style="display: flex; width: 100%; gap: 10px">
        <div style="flex: 8">
          <h1>
            <template v-for="data in dummy.title">
              {{ data }}
            </template>
          </h1>
        </div>
        <div class="status">
          {{ dummy.status }}
        </div>
      </div>

      <h2>
        {{ dummy.subject }}
      </h2>
      <div style="display: flex; gap: 10px; flex-direction: row">
        <div class="text-container">
          <div>
            <h4>
              모집기간
            </h4>
            <h3>
              {{ formatDate(dummy.recruitmentStart) }}~{{ formatDate(dummy.recruitmentEnd) }}
            </h3>
          </div>
          <div>
            <h4>
              진행기간
            </h4>
            <h3>
              {{ formatDate(dummy.flowStart) }}~{{ formatDate(dummy.flowEnd) }}
            </h3>
          </div>
          <div>
            <h4>
              결과발표
            </h4>
            <h3>
              {{ formatDate(dummy.announce) }}~{{ formatDate(dummy.recruitmentEnd) }}
            </h3>
          </div>
        </div>
        <div class="t">
          참가인원 {{ dummy.count }}명
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