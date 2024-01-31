<script lang="ts" setup>

import SkillSelector from "@/components/SkillSelector.vue";
</script>

<template>
  <div class="container">
    <h1>팀 등록</h1>
    <h2>팀명</h2>
    <input class="input" type="text">
    <h2>팀 설명</h2>
    <textarea class="input" placeholder="팀 설명 및 추구하는 방향에 대해 자유롭게 적어주세요" style="height: 177px"></textarea>
    <h2>사용할 기술</h2>
    <SkillSelector/>
    <h2>대표 이미지 등록</h2>
    <input class="input" type="text">
    <div class="button">등록하기</div>
  </div>
</template>

<style scoped>
.button {
  color: #F2F2F2;
  text-align: center;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  height: 40px;
  line-height: 39px;
  border: #7D3BFF solid 1px;
  border-radius: 5px;
  background: #7D3BFF;
  transition: 0.3s color ease;
}

.button:hover {
  color: #7D3CFF;
  background: white;
}

textarea {
  resize: none;
}

h1 {
  color: #303030;
  text-align: center;
  font-size: 28px;
  font-style: normal;
  font-weight: 600;
  line-height: normal;
  margin-bottom: 30px;
}

h2 {
  color: #303030;
  font-size: 18px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.input {
  width: 476px;
  height: 40px;
  border-radius: 5px;
  border: 1px solid #000;
  font-family: Pretendard-Regular, serif;
  color: #606060;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  padding: 10px;
}

.container {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin: 20px;
}

@media screen and (max-width: 698px) {
  .input {
    width: 100%;
  }
}
</style>