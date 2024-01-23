import { createStore } from 'vuex';

export default createStore({
    state() {
        return {
            token: localStorage.getItem('token') || null
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
