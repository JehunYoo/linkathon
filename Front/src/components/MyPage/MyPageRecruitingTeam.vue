<script setup lang="ts">

import UserCard from "@/components/User/UserCard.vue";
import {TeamService} from "@/api/TeamService.ts";

const teamService = new TeamService();
const idResponseDto = await teamService.getActiveTeamId();
const teamId = idResponseDto.id;
const suggestionListOfTeam = teamService.getSuggestionListOfTeam(teamId);

</script>

<template>
  <div class="recruiting-container">
    <h1>모집중인 팀</h1>
    <div class="remove-button">프로젝트 삭제</div>
  </div>
  <h2>프로젝트 이름</h2>
  <h3>프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.</h3>

  <div class="content">
    <div class="fx1">
      <h2>현재 팀원</h2>
      <div class="list">
        <template v-for="_ in 6">
          <UserCard :t="true"/>
        </template>
      </div>
    </div>
    <div class="fx1">
      <h2>신청한 사용자</h2>
      <div class="list">
        <template v-for="_ in 6">
          <UserCard :t="true"/>
        </template>
      </div>
    </div>
    <div class="fx1">
      <h2>권유한 사용자</h2>
      <div class="list">
        <template v-for="suggestion in suggestionListOfTeam">
          <UserCard :t="true"/>
        </template>
      </div>
    </div>
  </div>
</template>

<style scoped>
@media screen and (max-width: 1307px){
  .content {
    flex-direction: column;
  }
}
::-webkit-scrollbar {
  width: 4px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background:#A679FF;
}

::-webkit-scrollbar-thumb:hover {
  background: #7D3CFF;
}


.fx1 {
  flex: 1;
}

.list {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  gap:20px;
  overflow-y: scroll;
  overflow-x: hidden;
  max-height: 460px;
  padding-right: 10px;
}

.content {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
  margin-bottom: 60px;
}

.remove-button {
  width: 110px;
  height: 34px;
  border-radius: 5px;
  background: #FF6161;
  border: #FF6161 solid 1px;
  color: #F2F2F2;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: 33px;
  transition: color 0.3s ease;
}

.remove-button:hover {
  color: #FF6161;
  background: white;
}

.recruiting-container {
  display: flex;
}

h1 {
  flex: 1;
  color: #303030;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
}

h2 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  margin-top: 17px;
}

h3 {
  color: #303030;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 125% */
  max-width: 600px;
  margin-top: 12px;
}
</style>