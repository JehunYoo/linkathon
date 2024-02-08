<script lang="ts" setup>
import {Builder} from "builder-pattern";
import {HackathonInfoDTO} from "@/dto/hackathonInfoDTO.ts";

const dummy: HackathonInfoDTO[] = [];
dummy.push(
    Builder<HackathonInfoDTO>()
        .hackathonName(["제1회", "교육관련", "웹/앱 서비스제작", "해커톤"])
        .registerDate(new Date(2024, 0, 1))
        .teamDeadlineDate(new Date(2024, 0, 15))
        .startDate(new Date(2024, 1, 1))
        .endDate(new Date(2024, 1, 15))
        .hackathonTopic("교육 기술")
        .hackathonImageUrl("https://images.unsplash.com/photo-1528301721190-186c3bd85418?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D")
        .build(),
);
</script>

<template>
  <div class="hackathon-banner-list">
    <div v-for="data in dummy" class="hackathon-banner-list-container">
      <div class="list-title-container">
        <div v-for="title in data.hackathonName" class="list-title">
          {{ title }}
        </div>
      </div>
      <div class="img-container">
        <img :src="data.hackathonImageUrl" alt="">
      </div>
      <div class="title-container">
        <div class="title">
          {{ data.hackathonTopic }}
        </div>
        <div class="list-button">
          <div class="list-button-text">참여하기</div>
        </div>
      </div>
    </div>


  </div>
</template>

<style scoped>
img:hover {
  transform: scale(1.2);
}

.title {
  flex: 1;
  width: 100%;
  line-height: 31px;
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 600;
}

.title-container {
  margin-top: 13px;
  display: flex;
  width: 100%;
}

.list-button-text {
  flex: 1;

  text-align: center;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  vertical-align: middle;
  margin-top: 5px;
}

.list-button:hover {
  color: #ECEEF0;
  background: #7d3bff;
}

.list-button {
  transition: background-color 0.3s ease;
  color: #7D3CFF;
  width: 89px;
  height: 31px;
  border-radius: 4px;
  border: 1px solid #7D3CFF;
}

.list-title-container {
  z-index: 2;
  width: 300px;
  height: max-content;
  margin-top: 13px;
  margin-left: 13px;
  position: absolute;
  line-height: 23px;
}

img {
  transition: transform 0.2s ease;
  width: 100%;
  height: 200px;
  object-fit: cover;
  filter: brightness(0.6);

}

.img-container {
  width: 100%;
  height: 200px;
  border-radius: 10px;
  overflow: hidden;
}

.hackathon-banner-list {
  min-width: 300px;
  margin-top: 21px;
  display: flex;
  flex-direction: row;
  gap: 20px;
  flex-wrap: wrap;
}

.hackathon-banner-list-container {
  flex: 1;
  min-width: 300px;
  height: max-content;
  overflow: hidden;
}

.list-title {
  max-width: 100%;
  color: #F0F0F0;
  font-size: 20px;
  font-style: normal;
  font-weight: 800;
}
</style>