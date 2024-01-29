<script setup lang="ts">
import {Builder} from "builder-pattern";
import {ref, Ref} from "vue";

interface EditLink {
  edit: boolean,
  api: string,
  text: string,
}

const props = defineProps({
  editable: {
    type: Boolean,
    default: true
  }
});

const editList: EditLink[] = [];
editList.push(
    Builder<EditLink>()
        .api("https://www.naver.com")
        .text("깃허브 링크")
        .edit(false)
        .build()
);
editList.push(
    Builder<EditLink>()
        .api("https://www.naver.com")
        .text("프로젝트 링크")
        .edit(false)
        .build()
);
editList.push(
    Builder<EditLink>()
        .api("https://www.naver.com")
        .text("참고 링크")
        .edit(false)
        .build()
);

const editRef: Ref<Boolean>[] = [];
for (let i = 0; i < 3; i++) {
  const temp = ref<Boolean>(editList[i].edit)
  editRef.push(temp);
}
const editStart = (num: number) => {
  editRef[num].value = !editRef[num].value
}

</script>

<template>
  <div class="link-container">
    <h1>링크</h1>
    <template v-for="(edit,i) in editList">
      <div class="link-content-container" v-if="!editRef[i].value">
        <div style="flex: 1; padding-top: 3px">
          <h2>{{ edit.text }}</h2>
          <h3>https://www.naver.com</h3>
        </div>
        <div v-if="props.editable" class="button" @click="editStart(i)">
          수정
        </div>
      </div>
      <div class="link-content-container" v-if="editRef[i].value">
        <input :placeholder="edit.text + ' 입력'">
        <div v-if="props.editable" class="save-button" @click="editStart(i)">
          수정
        </div>
      </div>
    </template>

  </div>
</template>

<style scoped>
.save-button {
  width: 53px;
  height: 36px;
  border-radius: 5px;
  background: #7D3BFF;
  border: #7D3BFF solid 1px;
  color: #F2F2F2;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: 35px;
  transition: color 0.3s ease;
}

.save-button:hover {
  background: white;
  color: #7D3BFF;
}

input {
  padding-left: 12px;
  border-radius: 5px;
  border: 1px solid #303030;
  height: 36px;
  flex: 1;
  color: #606060;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.button {
  width: 53px;
  height: 36px;
  border-radius: 5px;
  border: 1px solid #7D3BFF;
  color: #303030;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
  line-height: 35px;
  transition: 0.3s ease color;
}

.button:hover {
  background: #7D3BFF;
  color: white;
}

.link-content-container {
  display: flex;
  gap: 12px;
}

.link-container {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

h1 {
  margin-top: 16px;
  color: #303030;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  line-height: 22.4px; /* 112% */
}

h2 {
  margin-left: 10px;
  color: #303030;
  font-size: 14px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
}

h3 {
  margin-left: 10px;
  color: #303030;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
</style>