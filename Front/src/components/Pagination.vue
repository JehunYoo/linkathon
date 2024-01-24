<script setup lang="ts">
import {ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";

const pageableDTO = ref({pageNumber: 1, totalPages: 5});
const route = useRoute();
const router = useRouter();

const updatePageFromQuery = () => {
  const queryPageNum = parseInt(route.query.page as string);
  if (!isNaN(queryPageNum) && queryPageNum >= 1 && queryPageNum <= pageableDTO.value.totalPages) {
    pageableDTO.value.pageNumber = queryPageNum;
  } else {
    pageableDTO.value.pageNumber = 1;
  }
};

updatePageFromQuery();

watch(() => route.query.page, updatePageFromQuery);

const movePage = (num: number) => {
  const newPageNumber = pageableDTO.value.pageNumber + num;
  if (newPageNumber >= 1 && newPageNumber <= pageableDTO.value.totalPages) {
    router.push({query: {...route.query, page: newPageNumber.toString()}});
  }
}

const moveNumberPage = (num: number) => {
  const newPageNumber = num;
  if (newPageNumber >= 1 && newPageNumber <= pageableDTO.value.totalPages) {
    router.push({query: {...route.query, page: newPageNumber.toString()}});
  }
}

</script>

<template>
  <div class="pagination-box">
    <svg v-if="pageableDTO.pageNumber > 1" @click="movePage(-1)" xmlns="http://www.w3.org/2000/svg" width="12"
         height="20" viewBox="0 0 12 20" fill="none">
      <g transform="scale(-1, 1) translate(-12, 0)">
        <path d="M2 18L10 10L2 2" stroke="#A9A9A9" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
      </g>
    </svg>
    <div v-for="n in pageableDTO.totalPages" :key="n"
         v-show="(pageableDTO.pageNumber === 1 && n <= 3) ||
             (pageableDTO.pageNumber === pageableDTO.totalPages && n >= pageableDTO.totalPages - 2) ||
             (n === pageableDTO.pageNumber || n === pageableDTO.pageNumber - 1 || n === pageableDTO.pageNumber + 1)"
         :class="n !== pageableDTO.pageNumber ? 'non-select' : 'select'" @click="moveNumberPage(n)">
      {{ n }}
    </div>

    <svg v-if="pageableDTO.pageNumber < pageableDTO.totalPages" @click="movePage(1)" xmlns="http://www.w3.org/2000/svg"
         width="12" height="20" viewBox="0 0 12 20" fill="none">
      <path d="M2 18L10 10L2 2" stroke="#A9A9A9" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
    </svg>
  </div>
</template>


<style scoped>
.pagination-box {
  text-align: center;
  font-family: "Jockey One", serif;
  font-size: 24px;
  font-style: normal;
  font-weight: 400;
  line-height: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  margin-top: 20px;
  margin-bottom: 80px;
}

.non-select {
  color: #A4A4A4;
}

.select {
  color: #7D3BFF;
}

</style>