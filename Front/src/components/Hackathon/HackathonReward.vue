<script setup lang="ts">

import HackathonRewardTeam from "@/components/Hackathon/HackathonRewardTeam.vue";
</script>

<template>
  <h1>수상자</h1>
  <div class="reward-container">
    <div class="reward">
      <h2 class="second">2</h2>
      <HackathonRewardTeam/>
    </div>
    <div class="reward">
      <h2 class="first">1</h2>
      <HackathonRewardTeam/>
    </div>
    <div class="reward">
      <h2 class="third">3</h2>
      <HackathonRewardTeam/>
    </div>
  </div>
</template>

<style scoped>
.reward-container {
  display: flex;
  gap: 20px;
}

.reward {
  flex: 1;
}

.first {
  color: #D5A11E;
}

.second {
  color: #A3A3A3;
}

.third {
  color: #CD7F32;
}

h2 {
  text-align: center;
  font-size: 60px;
  font-style: normal;
  font-weight: 900;
}

h1 {
  margin-top: 56px;
  color: #000;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px; /* 93.333% */
}
</style>