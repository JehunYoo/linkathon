import { createStore } from 'vuex';
import {UserSkillDTO} from "@/dto/tmpDTOs/UserSkillDTO.ts";

type Email = string;
type Field = string;

export default createStore({
    state() {
        return {
            token: localStorage.getItem('token') || null,
            email: '' as Email,
            field: '' as Field,
            skillSelectList: [] as UserSkillDTO[]
        };
    },
    mutations: {
        setToken(state, token) {
            state.token = token;
            localStorage.setItem('token', token);
        },
        clearToken(state) {
            state.token = null;
            localStorage.removeItem('token');
        },
        setEmail(state, newEmail) {
            state.email = newEmail;
        },
        setField(state, newField) {
            state.field = newField;
        },
        setSkillSelectList(state, list: UserSkillDTO[]) { // skillSelectList를 설정하는 뮤테이션
            state.skillSelectList = list;
        }
    },
    actions: {
        updateToken({ commit }, token) {
            commit('setToken', token);
        },
        removeToken({ commit }) {
            commit('clearToken');
        }
    },
    getters: {
        getToken: state => state.token
    }
});
