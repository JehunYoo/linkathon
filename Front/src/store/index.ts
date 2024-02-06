import { createStore } from 'vuex';

type Email = string;

export default createStore({
    state() {
        return {
            token: localStorage.getItem('token') || null,
            email: '' as Email
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
