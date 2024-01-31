<script setup lang="ts">
import SimpleUser from "@/components/User/SimpleUser.vue";
import {Builder} from "builder-pattern";

const leader: SimpleUserDTO = Builder<SimpleUserDTO>()
    .userId(1)
    .userImgUrl("https://i.namu.wiki/i/yVEttFa1u45vMA2qpwpP3y4lrMMq1nTRoiA1tgw14rK_5LHezLYrZcrOd3NzsN7FT5weKoF3cba5y9ygP81JKQ.webp")
    .introduce("안녕하세요. 고죠입니다.")
    .userName("고죠 사토루").build()

const member: SimpleUserDTO[] = [];
for (let i = 0; i < 5; i++) {
  member.push(leader);
}
</script>

<template>
  <h1>팀원 6</h1>
  <div class="team-container">
    <h1>팀장</h1>
    <div class="member-container">
      <SimpleUser :data="leader"/>
    </div>
    <h1>팀원</h1>
    <div v-for="m in member">
      <div style="border-bottom: #ccc 1px solid">
        <SimpleUser :data="m"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
h1 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px;
}

.team-container {
  margin-top: 12px;
  border-radius: 10px;
  border: 1px solid #7D3CFF;
  width: 100%;
  padding: 13px;
}
</style>