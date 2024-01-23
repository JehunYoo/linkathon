<script setup lang="ts">
import UserCard from "@/components/User/UserCard.vue";
import Pagination from "@/components/Pagination.vue";
import Modal from "@/components/Modal.vue";
import {ref} from "vue";
const clickedModal = ref<Number>();
const modalController = (num: number) => {
  clickedModal.value = num;
}
</script>

<template>
  <div class="user-card-container">
    <template v-for="i in 12">
      <Modal :modalController="modalController" v-if="clickedModal===i">
        바보같은 녀석 {{ i }}
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