<script lang="ts" setup>
import {Builder} from "builder-pattern";

const dummy: HackathonInfoDTO[] = [];
dummy.push(
    Builder<HackathonInfoDTO>()
        .title(["제1회", "교육관련", "웹/앱 서비스제작", "해커톤"])
        .recruitmentStart(new Date(2024, 0, 1))
        .recruitmentEnd(new Date(2024, 0, 15))
        .flowStart(new Date(2024, 1, 1))
        .flowEnd(new Date(2024, 1, 15))
        .announce(new Date(2024, 1, 20))
        .subject("교육 기술")
        .imgSrc("https://images.unsplash.com/photo-1528301721190-186c3bd85418?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D")
        .build(),
);
dummy.push(
    Builder<HackathonInfoDTO>()
        .title(["제2회", "게임관련", "해커톤"])
        .recruitmentStart(new Date(2024, 0, 1))
        .recruitmentEnd(new Date(2024, 0, 15))
        .flowStart(new Date(2024, 2, 15))
        .flowEnd(new Date(2024, 3, 15))
        .announce(new Date(2024, 3, 20))
        .subject("교육 기술")
        .imgSrc("https://png.pngtree.com/thumb_back/fh260/background/20230316/pngtree-heaven-gate-ladder-background-image_1948845.jpg")
        .build(),
);
dummy.push(
    Builder<HackathonInfoDTO>()
        .title(["제3회", "네트워크관련", "해킹 방어 대회", "해커톤"])
        .recruitmentStart(new Date(2024, 0, 1))
        .recruitmentEnd(new Date(2024, 0, 15))
        .flowStart(new Date(2024, 1, 1))
        .flowEnd(new Date(2024, 1, 15))
        .announce(new Date(2024, 1, 20))
        .subject("교육 기술")
        .imgSrc("https://png.pngtree.com/thumb_back/fh260/background/20230609/pngtree-three-puppies-with-their-mouths-open-are-posing-for-a-photo-image_2902292.jpg")
        .build(),
);
dummy.push(
    Builder<HackathonInfoDTO>()
        .title(["제3회", "네트워크관련", "해킹 방어 대회", "해커톤"])
        .recruitmentStart(new Date(2024, 0, 1))
        .recruitmentEnd(new Date(2024, 0, 15))
        .flowStart(new Date(2024, 1, 1))
        .flowEnd(new Date(2024, 1, 15))
        .announce(new Date(2024, 1, 20))
        .subject("교육 기술")
        .imgSrc("https://png.pngtree.com/thumb_back/fh260/background/20230609/pngtree-three-puppies-with-their-mouths-open-are-posing-for-a-photo-image_2902292.jpg")
        .build(),
);
</script>

<template>
  <div class="hackathon-banner-list">
    <div v-for="data in dummy" class="hackathon-banner-list-container">
      <div class="list-title-container">
        <div v-for="title in data.title" class="list-title">
          {{ title }}
        </div>
      </div>
      <div class="img-container">
        <img :src="data.imgSrc">
      </div>
      <div class="title-container">
        <div class="title">
          {{ data.subject }}
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
  width: 100%;
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
  min-width: 310px;
  margin-top: 21px;
  display: flex;
  flex-direction: row;
  gap: 20px;
  flex-wrap: wrap;
}

.hackathon-banner-list-container {
  flex: 1;
  min-width: 310px;
  height: max-content;
  overflow: hidden;
}

.list-title {
  color: #F0F0F0;
  font-size: 20px;
  font-style: normal;
  font-weight: 800;
}
</style>