<script setup lang="ts">
import {ref} from 'vue';
import draggable from 'vuedraggable';

interface Item {
  id: number;
  text: string;
}

const list = ref<Item[]>([
  {id: 1, text: 'Item 1'},
  {id: 2, text: 'Item 2'},
  {id: 3, text: 'Item 3'}
]);

const list2 = ref<Item[]>([
  {id: 4, text: 'Item 4'},
  {id: 5, text: 'Item 5'},
  {id: 6, text: 'Item 6'}
]);

const list3 = ref<Item[]>([
  {id: 7, text: 'Item 7'},
  {id: 8, text: 'Item 8'},
  {id: 9, text: 'Item 9'}
]);

const onEnd = () => {
  list.value.sort((a, b) => a.text.localeCompare(b.text));
  list2.value.sort((a, b) => a.text.localeCompare(b.text));
  list3.value.sort((a, b) => a.text.localeCompare(b.text));
};
</script>


<template>
  <div style="display: flex">
    <draggable v-model="list" item-key="id" group="items" @end="onEnd" class="drag-area">
      <template v-slot:item="{ element }">
        <div class="drag-item">
          {{ element.text }}
        </div>
      </template>
    </draggable>
    <draggable v-model="list2" item-key="id" group="items" @end="onEnd" class="drag-area">
      <template v-slot:item="{ element }">
        <div class="drag-item">
          {{ element.text }}
        </div>
      </template>
    </draggable>
    <draggable v-model="list3" item-key="id" group="items" @end="onEnd" class="drag-area">
      <template v-slot:item="{ element }">
        <div class="drag-item">
          {{ element.text }}
        </div>
      </template>
    </draggable>
  </div>
</template>


<style scoped>
.drag-area {
  padding: 10px;
  min-height: 50px; /* 최소 높이 설정 */
  border: 1px solid #ddd;
  flex: 1;
  z-index: 9;
  display: flex;
  flex-direction: column; /* 아이템들을 세로로 정렬 */
  justify-content: start; /* 아이템이 없을 때도 영역이 상단에 붙게 함 */
}


.drag-item {
  padding: 5px;
  margin: 5px;
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  cursor: move;
}
</style>
