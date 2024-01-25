import {createRouter, createWebHistory} from 'vue-router';
import Index from '@/view/Index.vue';
import Recruit from '@/view/Recruit.vue'
import Login from "@/view/Login.vue";
import Register from "@/view/Register.vue";
import FindAccount from "@/view/FindAccount.vue";
import PasswordChange from "@/view/PasswordChange.vue";
import DetailInfo from "@/view/DetailInfo.vue";

const routes = [
    {path: '/', component: Index},
    {path: '/recruit', component: Recruit},
    {path: '/register', component: Register},
    {path: '/login', component: Login},
    {path: '/findAccount', component: FindAccount},
    {path: '/passwordChange', component: PasswordChange},
    {path: '/detailInfo', component: DetailInfo},
];

const router = createRouter({
    history: createWebHistory('/'),
    routes,
    scrollBehavior() {
        return { top: 0 };
    },
});

export default router;
