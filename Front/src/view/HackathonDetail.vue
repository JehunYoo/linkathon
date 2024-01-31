<script lang="ts" setup>
import {Builder} from "builder-pattern";
import HackathonRecruiting from "@/components/Hackathon/HackathonRecruiting.vue";
import {ref, watch} from "vue";
import {useRoute} from "vue-router";
import HackathonLeaderBoard from "@/components/Hackathon/HackathonLeaderBoard.vue";
import HackathonReward from "@/components/Hackathon/HackathonReward.vue";

const test: HackathonInfoDetailDTO = Builder<HackathonInfoDetailDTO>()
    .flowStart(new Date())
    .flowEnd(new Date())
    .recruitmentStart(new Date())
    .recruitmentEnd(new Date())
    .announce(new Date())
    .subject("IT 교육 수강생 대상 서비스")
    .imgSrc("https://cdn.crowdpic.net/list-thumb/thumb_l_F25C5FD45B78842BE8B499E04852D8CB.jpg")
    .title(["제3회", "네트워크관련", "해킹 방어 대회", "해커톤"])
    .status("참가 신청중")
    .count(100)
    .hackathonId(1)
    .build()

function formatDate(date: Date): string {
  const year = date.getFullYear();
  const month = date.getMonth() + 1; // getMonth()는 0부터 시작
  const day = date.getDate();
  const weekdays = ["일", "월", "화", "수", "목", "금", "토"];
  const weekday = weekdays[date.getDay()];

  return `${year}. ${month}. ${day} (${weekday})`;
}

const route = useRoute();
const mode = ref<Number>(0);
const updatePageFromQuery = () => {
  const queryParam = route.query.mode;
  mode.value = parseInt(queryParam as string);
};

watch([() => route.query], updatePageFromQuery, {immediate: true});
</script>

<template>
  <h1>
    제 1회 교육관련 웹 / 앱 서비스 제작 해커톤
  </h1>
  <div class="title-container">
    <div class="hackathon-banner-list-container">
      <div class="list-title-container">
        <div v-for="title in test.title" class="list-title">
          {{ title }}
        </div>
      </div>
      <div class="img-container">
        <img :alt="test.subject" :src="test.imgSrc" class="img">
      </div>
    </div>
    <div class="text-holder" style="max-width: 290px">
      <h3>접수 기간</h3>
      <h4>{{ formatDate(test.recruitmentStart) }} ~ {{ formatDate(test.recruitmentEnd) }}</h4>
      <h3>대회 기간</h3>
      <h4>{{ formatDate(test.flowStart) }} ~ {{ formatDate(test.flowEnd) }}</h4>
      <h3>팀 최대 점수</h3>
      <h4>20점</h4>
      <h3>유의 사항</h3>
      <h5>어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.</h5>
    </div>
    <div class="text-holder mg60">
      <h3>참가 방법</h3>
      <h4>1. 어쩌구 저쩌구한다.</h4>
      <h4>2. 어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.</h4>
      <h4>3. 어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.</h4>
      <h4>4. 어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.어쩌구 저쩌구 한다.</h4>
    </div>
  </div>

  <HackathonLeaderBoard v-if="mode===1"/>
  <HackathonReward v-else-if="mode===2"/>
  <HackathonRecruiting v-else/>
</template>

<style scoped>

.mg60 {
  margin-left: 60px;
  max-width: 432px;
}

@media screen and (max-width: 1120px) {
  .title-container {
    flex-direction: column;
  }

  .img-container, .img {
    width: 100%;
    max-width: 100%;
  }

  .mg60 {
    margin-left: 0px;
    max-width: none;
  }
}

h1 {
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 32px;
  margin-bottom: 12px;
  margin-top: 40px;
}

h5 {
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px;
}

h4 {
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: 24px;
  margin-left: 13px;
  margin-bottom: 6px;
}

.text-holder {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.title-container {
  display: flex;
  gap: 20px;
}

h3 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px;
  border-left: #7D3BFF 3px solid;
  padding-left: 14px;
}

h2 {
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 32px;
  margin-bottom: 11px;
}

img:hover {
  transform: scale(1.2);
}

.list-title-container {
  z-index: 2;
  width: 300px;
  height: max-content;
  margin-top: 13px;
  margin-left: 13px;
  position: absolute;
  line-height: 34px;
}

.img {
  transition: transform 0.2s ease;
  width: 100%;
  height: 304px;
  object-fit: cover;
  filter: brightness(0.6);
}

.img-container {
  width: 100%;
  height: 304px;
  border-radius: 10px;
  overflow: hidden;
}

.hackathon-banner-list-container {
  min-width: 300px;
  height: max-content;
  overflow: hidden;
}

.list-title {
  max-width: 100%;
  color: #F0F0F0;
  font-size: 32px;
  font-style: normal;
  font-weight: 800;
}
</style>