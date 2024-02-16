<script lang="ts" setup>
import {Builder} from "builder-pattern";
import {onMounted, onUnmounted, ref} from 'vue';
import RollingBannerRadioButton from "@/components/RadioButton.vue";
import {HackathonInfoDTO} from "@/dto/hackathonInfoDTO.ts";

const dummy: HackathonInfoDTO[] = [];
dummy.push(
    Builder<HackathonInfoDTO>()
        .hackathonName(["AI 활용", "페이크 뉴스 및", "사이버 딥페이크 탐지", "개발 해커톤"])
        .registerDate(new Date(2024, 1, 14))
        .teamDeadlineDate(new Date(2024, 2, 5))
        .startDate(new Date(2024, 2, 10))
        .endDate(new Date(2024, 2, 15))
        .hackathonTopic("AI")
        .hackathonImageUrl("https://a602-link-bucket-jm.s3.ap-northeast-2.amazonaws.com/static/hackathonImage/markus-spiske-iar-afB0QQw-unsplash.jpg")
        .build(),
);

dummy.push(
    Builder<HackathonInfoDTO>()
        .hackathonName(["금융", "빅데이터", "해커톤"])
        .registerDate(new Date(2024, 1, 14))
        .teamDeadlineDate(new Date(2024, 4, 5))
        .startDate(new Date(2024, 4, 10))
        .endDate(new Date(2024, 4, 15))
        .hackathonTopic("핀테크")
        .hackathonImageUrl("https://a602-link-bucket-jm.s3.ap-northeast-2.amazonaws.com/static/hackathonImage/%EB%8C%80%ED%95%99%EC%83%9D%ED%95%B4%EC%BB%A4%ED%86%A4.webp")
        .build(),
);

dummy.push(
    Builder<HackathonInfoDTO>()
        .hackathonName(["글로벌", "스마트 시티", "개발 해커톤"])
        .registerDate(new Date(2024, 1, 14))
        .teamDeadlineDate(new Date(2024, 3, 5))
        .startDate(new Date(2024, 3, 10))
        .endDate(new Date(2024, 3, 15))
        .hackathonTopic("IoT")
        .hackathonImageUrl("https://a602-link-bucket-jm.s3.ap-northeast-2.amazonaws.com/static/hackathonImage/nasa-Q1p7bh3SHj8-unsplash.jpg")
        .build(),
);

dummy.push(
    Builder<HackathonInfoDTO>()
        .hackathonName(["개인 금융 관리", "앱/웹 개발", "해커톤"])
        .registerDate(new Date(2024, 1, 14))
        .teamDeadlineDate(new Date(2024, 5, 5))
        .startDate(new Date(2024, 5, 10))
        .endDate(new Date(2024, 5, 15))
        .hackathonTopic("금융 관리")
        .hackathonImageUrl("https://a602-link-bucket-jm.s3.ap-northeast-2.amazonaws.com/static/hackathonImage/%EC%9D%B8%EA%B3%B5%EC%A7%80%EB%8A%A5.webp")
        .build(),
);

const activeIndex = ref(0);

function formatDate(date: Date) {
  const month = date.getMonth() + 1;
  const day = date.getDate();
  return `${month}.${day}`;
}

function nextImage() {
  activeIndex.value = (activeIndex.value + 1) % dummy.length;
}

let intervalId: any;

onMounted(() => {
  intervalId = setInterval(nextImage, 7000);
});

onUnmounted(() => {
  clearInterval(intervalId);
});
</script>


<template>
  <div class="rolling-button">
    <div v-for="i in dummy.length" :key="i" @click="activeIndex=i-1;">
      <RollingBannerRadioButton :check="i-1===activeIndex"/>
    </div>
  </div>
  <div class="rolling-banner-container">
    <div v-for="(data, index) in dummy" :key="index">
      <div v-if="index === activeIndex" key="content">
        <div class="rolling-text-container">
          <div v-for="title in data.hackathonName" :key="title" class="rolling-title">
            {{ title }}
          </div>
          <div class="rolling-detail-container">
            <div class="date">
              <div class="content-title">모집기간</div>
              <div class="date-font">
                {{ formatDate(data.registerDate) }} -
                {{ formatDate(data.teamDeadlineDate) }}
              </div>
            </div>
            <div class="date">
              <div class="content-title">진행기간</div>
              <div class="date-font">
                {{ formatDate(data.startDate) }} -
                {{ formatDate(data.endDate) }}
              </div>
            </div>
          </div>
          <div style="margin-top: 20px">
            <div class="content-title">주제</div>
            <div class="subject-title"> {{ data.hackathonTopic }}</div>
          </div>
        </div>
        <div class="img-container">
          <div class="img-cover"></div>
          <img :src="data.hackathonImageUrl" alt="해커톤 이미지">
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

.rolling-button {
  position: absolute;
  display: flex;
  gap: 10px;
  z-index: 3;
  left: 50%;
  margin-top: 439px;
}

.date {
  margin-top: 40px;
}

.subject-title {
  color: #F0F0F0;
  font-size: 32px;
  font-style: normal;
  font-weight: 800;
}

.content-title {
  color: #F0F0F0;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: 25px;
  letter-spacing: 0.9px;
}

.date-font {
  color: #F0F0F0;
  font-size: 40px;
  font-style: normal;
  font-weight: 800;
  letter-spacing: 1.3px;
}

.rolling-detail-container {
  display: flex;
  gap: 40px;
}

.rolling-title {
  color: #F0F0F0;
  font-size: 48px;
  font-style: normal;
  font-weight: 800;
  line-height: 54px;
}

.img-cover {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
}

.rolling-text-container {
  max-width: 1355px;
  width: 100vw;
  overflow: hidden;
  padding: 20px;
  margin: auto;
  position: relative;
  z-index: 2;
  color: white;
  height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.rolling-banner-container {
  position: absolute;
  top: 80px;
  left: 0;
  height: 500px;
  width: 100vw;
  overflow: hidden;
}

.img-container {
  position: absolute;
  top: 0;
  left: 0;
  height: 500px;
  width: 100vw;
}

.img-container > img {
  position: absolute;
  width: 100vw;
  height: 100%;
  object-fit: cover;
}
</style>

