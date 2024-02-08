<script setup lang="ts">

import UserCard from "@/components/User/UserCard.vue";
import HackathonCard from "@/components/Hackathon/HackathonCard.vue";
import {onMounted, watch} from "vue";
import {TeamService} from "@/api/TeamService.ts";

const props = defineProps({
  teamId: {
    type:Number,
    default:0
  }
})
function getTeamInfo() {
  const teamService = new TeamService();
}

onMounted(() => {
  getTeamInfo()
})
watch(() => props.teamId, getTeamInfo, { immediate: true });
</script>

<template>
  <slot/>
  <h1>팀이름 - {{ teamId }}</h1>
  <h2>프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.프로젝트 설명입니다.</h2>
  <h1>해커톤 정보</h1>
  <HackathonCard/>
  <h1>팀장</h1>
  <div class="user-card-container">
    <div class="user-card">
      <UserCard/>
    </div>
  </div>
  <h1>현재 팀원</h1>
  <div class="user-card-container">
    <div v-for="_ in 5" class="user-card">
      <UserCard/>
    </div>
  </div>
</template>

<style scoped>
.user-card-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  flex-direction: column;
}

.user-card {
  flex: 1;
}

h1 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  margin-top: 17px;
  margin-bottom: 20px;
}

h2 {
  max-width: 600px;
  margin-top: 12px;
}
</style>