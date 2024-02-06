<script lang="ts" setup>
import {PropType, ref, watch} from "vue";
import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();

const props = defineProps({
  pageName: {
    type:String,
    default:"page"
  },
  pageableDTO: {
    type:Object as PropType<PageableDto>,
    default: ref({pageNumber:1, totalPages:5})
  }
});

const pageableDTO = ref<PageableDto>(props.pageableDTO);
const updatePageFromQuery = () => {
  const queryParam = props.pageName ? route.query[props.pageName] : route.query.page;
  const queryPageNum = parseInt(queryParam as string);

  if (!isNaN(queryPageNum) && queryPageNum >= 1 && queryPageNum <= pageableDTO.value.totalPages) {
    pageableDTO.value.pageNumber = queryPageNum;
  } else {
    pageableDTO.value.pageNumber = 1;
  }
};

watch([() => route.query, () => props.pageName], updatePageFromQuery, { immediate: true });


const movePage = (num: number) => {
  const newPageNumber = pageableDTO.value.pageNumber + num;
  if (newPageNumber >= 1 && newPageNumber <= pageableDTO.value.totalPages) {
    router.push({query: {...route.query, [props.pageName as string]: newPageNumber.toString()}});
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
    <svg v-if="pageableDTO.pageNumber > 1" fill="none" height="20" viewBox="0 0 12 20"
         width="12" xmlns="http://www.w3.org/2000/svg" @click="movePage(-1)">
      <g transform="scale(-1, 1) translate(-12, 0)">
        <path d="M2 18L10 10L2 2" stroke="#A9A9A9" stroke-linecap="round" stroke-linejoin="round" stroke-width="3"/>
      </g>
    </svg>

    <template v-for="n in pageableDTO.totalPages">
      <div  v-if="(pageableDTO.pageNumber === 1 && n <= 3) ||
             (pageableDTO.pageNumber === pageableDTO.totalPages && n >= pageableDTO.totalPages - 2) ||
             (n === pageableDTO.pageNumber || n === pageableDTO.pageNumber - 1 || n === pageableDTO.pageNumber + 1)"
            :key="n"
            :class="n !== pageableDTO.pageNumber ? 'non-select' : 'select'" @click="moveNumberPage(n)">
        {{ n }}
      </div>
    </template>


    <svg v-if="pageableDTO.pageNumber < pageableDTO.totalPages" fill="none" height="20"
         viewBox="0 0 12 20" width="12" xmlns="http://www.w3.org/2000/svg" @click="movePage(1)">
      <path d="M2 18L10 10L2 2" stroke="#A9A9A9" stroke-linecap="round" stroke-linejoin="round" stroke-width="3"/>
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
}

.non-select {
  color: #A4A4A4;
}

.select {
  color: #7D3BFF;
}

</style>