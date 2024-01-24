<script lang="ts" setup>
import UserCard from "@/components/User/UserCard.vue";
import Pagination from "@/components/Pagination.vue";
import Modal from "@/components/Modal/Modal.vue";
import {ref} from "vue";
import ModalEffect from "@/components/Modal/ModalEffect.vue";
import ModalMember from "@/components/Modal/ModalMember.vue";

const clickedModal = ref<Number>();
const modalController = (num: number) => {
  clickedModal.value = num;
}
</script>

<template>
  <div class="user-card-container">
    <template v-for="i in 12">
      <Modal v-if="clickedModal===i" :modalController="modalController">
        <ModalEffect text="남은점수 4점"/>
        <ModalMember/>
      </Modal>
      <UserCard @click="modalController(i)"/>
    </template>
  </div>
  <Pagination/>
</template>

<style scoped>
.user-card-container {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}
</style>