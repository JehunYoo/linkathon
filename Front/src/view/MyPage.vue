<script setup lang="ts">
import {useRoute} from "vue-router";
import {ref, watch} from "vue";
import MyPageRecruitingTeam from "@/components/MyPage/MyPageRecruitingTeam.vue";
import MyInfo from "@/components/MyPage/MyInfo.vue";
import MyPageRecruitTeamInfo from "@/components/MyPage/MyPageRecruitTeamInfo.vue";
import MyPageMyProject from "@/components/MyPage/MyPageMyProject.vue";
import MyPageSchedule from "@/components/MyPage/MyPageSchedule.vue";
import {UserService} from "@/api/UserService.ts";

const userService = new UserService();
const route = useRoute();
const mode = ref<number>(0);
const updatePageFromQuery = () => {
  const queryParam = route.query.mode;
  mode.value = parseInt(queryParam as string);
  if (isNaN(mode.value)) mode.value=0;
};

const fetchData = function (){
  try {
    const response = await userService.getUserData(); // 실제 API 엔드포인트에 맞게 수정
    console.log(response.data); // 가져온 데이터 확인
  } catch (error) {
    console.error('Error fetching data:', error);
  }
}


watch([() => route.query], updatePageFromQuery, {immediate: true});
</script>

<template>
  <div class="myPage-container">
    <div class="content">
      <MyPageRecruitTeamInfo v-if="mode===1">
        <div class="title-container">
          <h1>신청한 팀</h1>
          <div class="remove-button">신청 취소</div>
        </div>
      </MyPageRecruitTeamInfo>
      <MyPageRecruitTeamInfo v-else-if="mode===2">
        <div class="title-container">
          <h1>권유받은 팀</h1>
          <div class="accept-button">수락</div>
          <div class="remove-button">거절</div>
        </div>
      </MyPageRecruitTeamInfo>
      <MyPageMyProject v-else-if="mode===3"/>
      <MyPageRecruitingTeam v-else-if="mode===4"/>
      <MyPageSchedule v-else-if="mode===5"/>
      <MyInfo v-else/>
    </div>
    <table class="menu">
      <tr>
        <td :class="{'select':mode==0}">
          <router-link to="?mode=0">내 정보</router-link>
        </td>
      </tr>
      <tr>
        <td :class="{'select':mode==1}">
          <router-link to="?mode=1">신청한 팀</router-link>
        </td>
      </tr>
      <tr>
        <td :class="{'select':mode==2}">
          <router-link to="?mode=2">권유받은 팀</router-link>
        </td>
      </tr>
      <tr>
        <td :class="{'select':mode==3}">
          <router-link to="?mode=3">내 프로젝트</router-link>
        </td>
      </tr>
      <tr>
        <td :class="{'select':mode==4}">
          <router-link to="?mode=4">모집중인 팀</router-link>
        </td>
      </tr>
      <tr>
        <td :class="{'select':mode==5}">
          <router-link to="?mode=5">스케쥴 관리</router-link>
        </td>
      </tr>
      <tr>
        <td :class="{'select':mode==6}">
          <router-link to="/exit"><span>회원 탈퇴</span></router-link>
        </td>
      </tr>
    </table>
  </div>
</template>

<style scoped>

.accept-button {
  background: #7D3BFF;
  border: #7D3BFF solid 1px;
}

.accept-button:hover {
  color: #7D3BFF;
  background: white;
}

h1 {
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  flex: 1;
  margin-bottom: 20px;
}

.title-container {
  display: flex;
  gap: 10px;
}

.accept-button, .remove-button {
  padding-left: 16px;
  padding-right: 16px;
  height: 34px;
  border-radius: 5px;
  color: #F2F2F2;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: 33px;
  transition: color 0.3s ease;
}

.remove-button {
  background: #FF6161;
  border: #FF6161 solid 1px;
}

.remove-button:hover {
  color: #FF6161;
  background: white;
}

@media screen and (max-width: 697px) {
  .myPage-container {
    flex-direction: column-reverse;
  }
}

@media screen and (min-width: 698px) {
  .menu {
    max-width: 255px;
  }
}

.select {
  background: #7D3BFF;
  color: #FFF;
  border: #7D3BFF solid 1px;
}

.select a {
  color: #FFF;
}

.myPage-container {
  margin-top: 20px;
  display: flex;
  gap: 20px;
  margin-bottom: 50px;
}

.content {
  flex: 1;
}

.menu {
  width: 100%;
  border-collapse: separate;
  border-radius: 10px;
  overflow: hidden;
  border: #DEDEDE solid 1px;
  border-bottom: none;
  height: max-content;
}

td {
  padding: 13px 13px 13px 18px;
  border-bottom: #DEDEDE solid 1px;
}

a {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 22.4px;
  width: 250px;
}
</style>