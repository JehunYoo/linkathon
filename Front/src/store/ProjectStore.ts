import { createStore } from 'vuex';
import {ProjectService} from "@/api/ProjectService.ts";

export default createStore({
    state() {
        return {
            projectService: new ProjectService(),
        };
    },
    mutations: {
        // setToken(state, token) {
        //     state.token = token;
        //     localStorage.setItem('token', token);
        // },
        // clearToken(state) {
        //     state.token = null;
        //     localStorage.removeItem('token');
        // }
    },
    actions: {
        // updateToken({ commit }, token) {
        //     commit('setToken', token);
        // },
        // removeToken({ commit }) {
        //     commit('clearToken');
        // }
    },
    getters: {
        getProjectService: state => state.projectService,

    }
});
