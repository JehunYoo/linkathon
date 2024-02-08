<script setup lang="ts">
import SkillIcon from "@/components/Skill/SkillIcon.vue";
import {onMounted, PropType, ref, Ref} from "vue";
import {WinnerProjectResponseDto} from "@/dto/tmpDTOs/hackathonDTO.ts";
import {HackathonService} from "@/api/HackathonService.ts"

// const skill = Builder<SkillDTO>()
// .skillId(1)
// .skillName("Java")
// .skillImgUrl("data:image/png;base64,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")
// .build();
const props = defineProps({
  data : {
    type : Object as PropType<WinnerProjectResponseDto>,
    required: true
  },
  id : {
    type : Number,
    required : true
  }
});
console.log("cc",props.data?.winnerProjectInfoDto)
</script>

<template>
  <div class="container">

    <img
        src="https://post-phinf.pstatic.net/MjAxOTA5MjNfMjQx/MDAxNTY5MjI0NzIzMzY4.J7AkYT8wZ9wSOklBQbkrHAnfKbKHEGzwDmesytQpLpMg.9bJo6BvINkAC0vtz6RS1Os_9TJ8mS1SYU_r64BMv22Ig.JPEG/TJA.jpg?type=w800_q75"
        alt=""/>
    <h1>프로젝트 설명</h1>
    <section>
      {{props.data.winnerProjectInfoDto?.projectDesc}}
    </section>
    <h1>팀원 목록</h1>
    <div class="member-img-container">
      <img :src=member.userImageUrl alt=""
           class="member-img" v-for="member in props.data.teamResponseDto?.members">

    </div>
    <h1>기술 스택</h1>
    {{props.data.teamResponseDto}}
    {{props.data.teamResponseDto?.teamSkills}}
    <div class="skill-container">
      <div v-for="skill in props.data?.teamResponseDto?.teamSkills">
        <SkillIcon :skill="skill" width="52px" height="52px" radius="10px"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.skill-container {
  margin-top: 12px;
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  justify-content: center;
}

.member-img-container {
  display: flex;
  gap: 22px;
  flex-wrap: wrap;
  margin-top: 8px;
}

.member-img {
  max-width: 110px;
  height: 110px;
  object-fit: cover;
  border: 1px solid #303030;
}

section {
  color: #303030;
  text-align: justify;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 24px; /* 150% */
  margin-top: 8px;
}

h1 {
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px; /* 112% */
  margin-top: 20px;
}

.container {
  margin-top: 27px;
  border-radius: 10px;
  border: 2px solid #7D3BFF;
  background: #FFF;
  padding: 24px;
  margin-bottom: 60px;
}

img {
  max-width: 379px;
  border-radius: 5px;
}
</style>