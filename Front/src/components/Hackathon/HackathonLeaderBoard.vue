<script lang="ts" setup>

import Pagination from "@/components/Pagination.vue";
import {HackathonService} from "@/api/HackathonService.ts";
import {
  PageableProceedingHackathons
} from "@/dto/tmpDTOs/hackathonProceedingProjectResponseDto.ts";
import {onMounted, Ref, ref} from "vue";

const props = defineProps({
  id : {
    type : Number,
    required : true
  }
});

const hackathonService = new HackathonService();
const pageableRef = ref({});

const hackathonLeaderboard : Ref<PageableProceedingHackathons> = ref({} as PageableProceedingHackathons);
onMounted(async () => {
  hackathonLeaderboard.value = await hackathonService.getProceedingLeaderboards(props.id);
  pageableRef.value = hackathonLeaderboard.value.pageable;
})

</script>

<template>
  <h1>리더보드</h1>
  <table>
    <tr>
      <th>#</th>
      <th>팀명</th>
      <th>팀 멤버</th>
      <th>점수</th>
    </tr>

    <tr class="high-right" v-for="(data,i) in hackathonLeaderboard.hackathons">
      <td>{{i+1}}</td>
      <td>{{ data.teamName }}</td>
      <td>{{ data.teamMembers.join(', ') }}</td>
      <td>{{ data.hackathonScore }}</td>
    </tr>
  </table>
  <Pagination :pageable-d-t-o="hackathonLeaderboard.pageable" style="margin-bottom: 60px"/>
</template>

<style scoped>
.high-right {
  background: rgba(125, 59, 255, 0.10);
}

h1 {
  color: #000;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px;
  margin-top: 56px;
  margin-bottom: 18px;
}

table {
  width: 100%;
  text-align: center;
}

th {
  color: #606060;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px;
  padding: 10px;
}

td {
  padding: 20px;
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px;
}

tr, table, th, td {
  border-top: #909090 solid 1px;
  border-bottom: #909090 solid 1px;
}
</style>