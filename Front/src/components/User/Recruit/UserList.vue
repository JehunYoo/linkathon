<script setup lang="ts">

import UserCard from "@/components/User/UserCard.vue";
import Pagination from "@/components/Pagination.vue";
</script>

<template>
  <div class="user-card-container">
    <div class="flex-box">
      <UserCard/>
      <UserCard/>
    </div>
    <div class="flex-box">
      <UserCard/>
      <UserCard/>
    </div>
    <div class="flex-box">
      <UserCard/>
      <UserCard/>
    </div>
    <div class="flex-box">
      <UserCard/>
      <UserCard/>
    </div>
    <div class="flex-box">
      <UserCard/>
      <UserCard/>
    </div>
    <div class="flex-box">
      <UserCard/>
      <UserCard/>
    </div>
    <div class="flex-box">
      <UserCard/>
      <UserCard/>
    </div>
    <div class="flex-box">
      <UserCard/>
      <UserCard/>
    </div>
  </div>
  <Pagination/>
</template>

<style scoped>
.flex-box {
  flex: 1;
  flex-wrap: wrap;
  display: flex;
  gap: 20px;
}
.user-card-container {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}
</style>