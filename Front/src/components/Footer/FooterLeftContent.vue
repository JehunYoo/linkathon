<script lang="ts" setup>

import FooterInstaButton from "@/components/Footer/FooterInstaButton.vue";
import FooterNaverButton from "@/components/Footer/FooterNaverButton.vue";
import FooterYoutubeButton from "@/components/Footer/FooterYoutubeButton.vue";
</script>

<template>
  <div class="footer-container">
    <div class="footer-menu">
      <div>이용약관</div>
      <div>|</div>
      <div>개인정보처리방침</div>
      <div>|</div>
      <div>제휴문의</div>
    </div>
    <div class="footer-text">
      푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는
      칸입니당
    </div>
    <div class="footer-text">
      푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는 칸입니당 푸터 내용을 적는
      칸입니당
    </div>
    <div class="footer-icon">
      <FooterInstaButton/>
      <FooterNaverButton/>
      <FooterYoutubeButton/>
    </div>
    <div class="footer-copyright">
      ©2023 LINK. Inc. All rights reserved.
    </div>
  </div>

</template>

<style scoped>
@media (max-width: 768px) {
  .footer-container {
    padding: 20px;
  }

  .footer-text {
    width: 100%;
  }
}

@media (min-width: 768px) {
  .footer-text {
    max-width: 420px;
  }
}

.footer-icon {
  display: flex;
  margin-top: 24px;
  gap: 20px;
  width: max-content;
}

.footer-menu {
  width: 100%;
  max-width: 360px;
  display: flex;
  gap: 16px;
}

.footer-menu div {
  color: #6F7070;
  text-align: center;
  font-size: 14px;
  font-style: normal;
  font-weight: 500;
  line-height: 120%;
}

.footer-container {
  width: 100%;
  flex: 3;
  display: flex;
  flex-direction: column;
}

.footer-text {
  color: #6F7070;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 150%;

  width: 100%;
  margin-top: 20px;
}

.footer-copyright {
  margin-top: 20px;
  color: #6F7070;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 150%;
  width: max-content;
}
</style>