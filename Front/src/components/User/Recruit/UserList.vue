<script lang="ts" setup>
import UserCard from "@/components/User/UserCard.vue";
import Pagination from "@/components/PaginationV2.vue";
import Modal from "@/components/Modal/Modal.vue";
import {PropType, ref, watch} from "vue";
import ModalMember from "@/components/Modal/ModalMember.vue";
import {Builder} from "builder-pattern";
import {TeamMemberFindDTO} from "@/dto/tmpDTOs/teamBuildingDTO.ts";
import ModalButton from "@/components/Modal/ModalButton.vue";
import {TeamService} from "@/api/TeamService.ts";
import PaginationV2 from "@/components/PaginationV2.vue";

const clickedModal = ref<Number>();
const handleModalClose = (num: number) => {
  clickedModal.value = num;
}
const props = defineProps({
  refUser: {
    type: Object as PropType<TeamMemberFindDTO>,
    default: Builder<TeamMemberFindDTO>().build()
  },
});

const pageableDto = ref<PageableDto>({
  pageNumber: props?.refUser.number+1,
  totalPages: props?.refUser.totalPages
});

watch(() => props.refUser, (newVal) => {
  pageableDto.value.pageNumber = newVal.number+1;
  pageableDto.value.totalPages = newVal.totalPages;
});



async function suggestTeam(userId: number) {
  const teamService = new TeamService();
  const team = await teamService.getActiveTeamId();
  const teamId = team.id;
  teamService.postSuggestionByTeam(teamId, userId);
}
</script>

<template>
  <div class="user-card-container">
    <template v-if="refUser?.content">
      <template v-for="(data, i) in refUser?.content">
        <Modal v-if="clickedModal===i+1" @closeModal="handleModalClose">
          <ModalMember :userInfo="data" :user-id="i">
            <ModalButton button-text="합류 요청" @click-event="suggestTeam(i)"/>
          </ModalMember>
        </Modal>
        <UserCard @click="handleModalClose(i+1)" :userInfo="data"/>
      </template>
    </template>
  </div>
  <PaginationV2 style="margin-bottom: 60px" :pageableDTO="pageableDto" />
</template>

<style scoped>
.user-card-container {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}
</style>