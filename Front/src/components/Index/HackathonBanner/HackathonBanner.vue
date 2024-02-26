<script lang="ts" setup>
import HackathonBannerList from "@/components/Index/HackathonBanner/HackathonList.vue";
import IndexSubMenu from "@/components/Index/IndexSubMenu.vue";
import {ref, Ref} from "vue";
import {PageableHackathonList} from "@/dto/hackathonInfoDTO.ts";
import {HackathonService} from "@/api/HackathonService.ts";
import {onMounted} from "vue";

const hackathonsRef: Ref<PageableHackathonList> = ref({} as PageableHackathonList);
const hackathonService : HackathonService = new HackathonService();
onMounted(async () => {
  hackathonsRef.value = await hackathonService.getHackathonList("모집중", 0, 4);
});

</script>

<template>
  <div class="recruiting-container">
    <IndexSubMenu title="모집중인 해커톤"/>
    <template v-if="hackathonsRef">
      <HackathonBannerList :ht="hackathonsRef"/>
    </template>
  </div>
</template>

<style scoped>
.recruiting-container {
  margin-top: 543px;
}
</style>