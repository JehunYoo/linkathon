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
      본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포 등을 금합니다.
    </div>
    <div class="footer-text">
      주윤[팀장]  https://github.com/jooyun-1<br>
      김지민 https://github.com/jjmsrc<br>
      마성진 https://github.com/MaSeongJin<br>
      유제훈 https://github.com/JehunYoo<br>
      임종률 https://github.com/Jong-Youl<br>
      조민균 https://github.com/jmg9776<br>
    </div>
    <div class="footer-icon">
      <FooterInstaButton/>
      <FooterNaverButton/>
      <FooterYoutubeButton/>
    </div>
    <div class="footer-copyright">
      ©2024 LINK. Inc. All rights reserved.
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