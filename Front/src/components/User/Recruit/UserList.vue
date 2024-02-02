<script lang="ts" setup>
import UserCard from "@/components/User/UserCard.vue";
import Pagination from "@/components/Pagination.vue";
import Modal from "@/components/Modal/Modal.vue";
import {onMounted, Ref, ref} from "vue";
import ModalMember from "@/components/Modal/ModalMember.vue";
import {TeamBuildingService} from "@/api/TeamBuildingService.ts";
import {TeamMemberFindDTO} from "@/dto/tmpDTOs/teamBuildingDTO.ts";
import {Builder} from "builder-pattern";

const clickedModal = ref<Number>();
const handleModalClose = (num: number) => {
  clickedModal.value = num;
}
const teamBuildingService = new TeamBuildingService();
const refData: Ref<TeamMemberFindDTO[]> = ref([]);
onMounted(async () => {
  refData.value = await teamBuildingService.getALlTeamFindMember();
})

const pageableDTO = Builder<PageableDto>().pageNumber(1).totalPages(10).build();

</script>

<template>
  <div class="user-card-container">
    <template v-for="i in 16">
      <Modal v-if="clickedModal===i" @closeModal="handleModalClose">
        <ModalMember/>
      </Modal>
      <UserCard @click="handleModalClose(i)"/>
    </template>
  </div>
  <Pagination style="margin-bottom: 60px" :pageableDTO="pageableDTO"/>
</template>

<style scoped>
.user-card-container {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}
</style>