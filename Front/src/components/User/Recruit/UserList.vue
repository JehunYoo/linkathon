<script lang="ts" setup>
import UserCard from "@/components/User/UserCard.vue";
import Pagination from "@/components/Pagination.vue";
import Modal from "@/components/Modal/Modal.vue";
import {ref} from "vue";
import ModalMember from "@/components/Modal/ModalMember.vue";

const clickedModal = ref<Number>();
const handleModalClose = (num: number) => {
  clickedModal.value = num;
}
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
  <Pagination style="margin-bottom: 60px"/>
</template>

<style scoped>
.user-card-container {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}
</style>