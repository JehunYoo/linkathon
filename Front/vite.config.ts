import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import ViteImagemin from 'vite-plugin-imagemin';

export default defineConfig({
  server: {
    // 개발 서버 호스트를 '0.0.0.0'로 설정하여 외부 접근을 허용합니다.
    host: '0.0.0.0',
  },
  plugins: [
    vue(),
    ViteImagemin({
      gifsicle: { optimizationLevel: 3 },
      optipng: { optimizationLevel: 5 },
      mozjpeg: { quality: 80 },
      pngquant: { quality: [0.7, 0.9] },
    }),
  ],
  resolve: {
    alias: {
      '@': new URL('./src', import.meta.url).pathname
    }
  }
})
