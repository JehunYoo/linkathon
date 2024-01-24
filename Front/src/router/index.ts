import {createRouter, createWebHistory} from 'vue-router';
import Index from '@/view/Index.vue';
import Recruit from '@/view/Recruit.vue'
import Login from "@/view/Login.vue";
import Register from "@/view/Register.vue";
import FindAccount from "@/view/FindAccount.vue";

const routes = [
    {path: '/', component: Index},
    {path: '/recruit', component: Recruit},
    {path: '/register', component: Register},
    {path: '/login', component: Login},
    {path: '/findAccount', component: FindAccount},
];

const router = createRouter({
    history: createWebHistory('/'),
    routes,
    scrollBehavior() {
        return { top: 0 };
    },
});

export default router;
