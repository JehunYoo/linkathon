<script lang="ts" setup>

import DropDownBox from "@/components/DropDownBox.vue";

const tierSelect = (
  ["선택", "브론즈", "실버", "골드", "다이아"]
);


</script>

<template>
  <div class="user-info-category-container">
    <div>
      <div class="user-info-category-text">티어</div>
      <div class="dropdown-container">
        <DropDownBox :tierSelect="tierSelect"/>
      </div>
    </div>
    <div>
      <div class="user-info-category-text">기타</div>
      <div class="dropdown-container">
        <DropDownBox/>
        <DropDownBox/>
        <DropDownBox/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.dropdown-container {
  display: flex;
  gap: 8px;
}

.user-info-category-container {
  flex: 1;
  display: flex;
  gap: 14px;
}

.user-info-category-text {
  margin-bottom: 20px;
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  padding-left: 6px;
  border-left: #7D3BFF 2px solid;
}


</style>