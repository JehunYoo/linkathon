import {createRouter, createWebHistory} from 'vue-router';
import Index from '@/view/Index.vue';
import Recruit from '@/view/Recruit.vue'

const routes = [
    {path: '/', component: Index},
    {path: '/recruit', component: Recruit}
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
