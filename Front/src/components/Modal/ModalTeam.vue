<script lang="ts" setup>
import {Builder} from "builder-pattern";
import UserCard from "@/components/User/UserCard.vue";
import ModalEffect from "@/components/Modal/ModalEffect.vue";

const dummy: HackathonTeamInfoDetailDTO = Builder<HackathonTeamInfoDetailDTO>()
    .maxPoint(7)
    .nowPoint(4)
    .maxMember(6)
    .nowMember(3)
    .imgUrl("https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/09/18/1e586277-48ba-4e8a-9b98-d8cdbe075d86.jpg")
    .teamName("카리나와 함께")
    .projectContent("팀 설명 및 주제 설명 팀 설명 및 주제 설명 팀 설명 및 주제 설명  팀 설명 및 주제 설명 팀 설명 및 주제 설명팀 설명 및 주제 설명팀 설명 및 주제 설명팀 설명 및 주제 설명 팀 설명 및 주제 설명팀 설명 및 주제 설명 팀 설명 및 주제 설명 팀 설명 및 주제 설명 팀 설명 및 주제 설명ㅍ팀 설명 및 주제 설명팀 설명 및 주제 설명 팀 설명 및 주제 설명팀 설명 및 주제 설명 팀 설명 및 주제 설명팀 설명 및 주제 설명")
    .build();


</script>

<template>
  <ModalEffect v-if="(dummy.maxPoint-dummy.nowPoint) > 0 && dummy.maxMember > dummy.nowMember"
               :text="'남은 점수 '+ (dummy.maxPoint-dummy.nowPoint) + '점'"/>
  <div style="padding: 24px;">
    <div class="container">
      <img :src="dummy.imgUrl" alt="" class="img">
      <div class="team-container">
        <h1>{{ dummy.teamName }}</h1>
        <section>
          {{ dummy.projectContent }}
        </section>
        <div style="display: flex; justify-content: right">
          <div
              :class="!((dummy.maxPoint-dummy.nowPoint) > 0 && dummy.maxMember > dummy.nowMember)?'button':'button-clickable'">
            참가 신청
          </div>
        </div>
      </div>
    </div>
    <h2>현재팀원</h2>
    <div class="user-card-container">
      <UserCard v-for="_ in 6" :t="true"/>
    </div>
  </div>
</template>

<style scoped>
* {
  -ms-overflow-style: none;
}

::-webkit-scrollbar {
  display: none;
}

.button-clickable {
  width: 86px;
  height: 34px;
  border-radius: 5px;
  background: #7D3CFF;
  border: 1px #7D3CFF solid;
  color: #F2F2F2;
  text-align: center;
  line-height: 33px;
  transition: 0.3s color ease;
}

.button-clickable:hover {
  color: #7D3CFF;

  background: white;
}

.user-card-container {
  max-width: 658px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  max-height: 225px;
  overflow: scroll;
}

h2 {
  margin-top: 10px;
  margin-bottom: 11px;
  color: #000;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 22.4px;
}

.button {
  width: 86px;
  height: 34px;
  border-radius: 5px;
  background: #BEBEBE;
  color: #F2F2F2;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: 33px;
}

.team-container {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

h1 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: 22.4px;
}

section {
  max-width: 350px;
  color: #303030;
  text-align: justify;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 24px;
  flex: 1;
}

.container {
  display: flex;
  gap: 20px;
}

.img {
  width: 280px;
  border-radius: 6px;
  height: 280px;
  object-fit: cover;
}

@media screen and (max-width: 698px) {
  .img {
    width: calc(100% - 43px);
  }

  .user-card-container {
    max-height: none;
  }

  .container {
    flex-direction: column;
  }

  section {
    max-width: none;
  }
}
</style>