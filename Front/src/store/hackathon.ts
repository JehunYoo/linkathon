import { createStore } from 'vuex';

export default createStore({
    state() {
        return {
            statusName: ''
        };
    },
    mutations: {
        setStatusName(state, name) {
            state.statusName = name;
        },
    },
    actions: {
        updateStatusName({ commit }, name) {
            commit('setStatusName', name);
        },
    },
    getters: {
        getStatusName: (state) => state.statusName,
    }
});
