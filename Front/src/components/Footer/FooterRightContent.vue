<script setup lang="ts">

</script>

<template>
  <div class="footer-container">
    <div class="footer-box">
      <div class="footer-link-title">링크. | LINK.</div>
      <div class="footer-text">010-1234-1234</div>
      <div class="footer-text">상담 시간: 09:00 ~ 18:00</div>
      <div class="footer-text">점심 시간: 11:40 ~ 13:00</div>
      <div class="footer-text">(주말 및 공휴일에는 상담이 지연될 수 있습니다.)</div>
    </div>
  </div>
</template>

<style scoped>
@media (max-width: 768px) {
  .footer-container {
    padding: 20px;
  }
}

.footer-box {
  width: max-content;
}

.footer-container {
  flex: 0;
  display: flex;
  flex-direction: column;
  justify-content: right;
}

.footer-link-title {
  color: #6F7070;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  line-height: 120%;
  margin-bottom: 10px;
}

.footer-text {
  color: #6F7070;
  font-size: 14px;
  font-style: normal;
  font-weight: 400;
  line-height: 150%;
  max-width: max-content;
}
</style>