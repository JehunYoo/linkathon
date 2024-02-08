import { createStore } from 'vuex';

export default createStore({
    state() {
        return {
            // OpenVidu objects
            OV: undefined, // OpenVidu 통합 통신, 관리 객체
            session: undefined, //

            mainStreamManager: undefined,
            publisher: undefined,
            subscribers: [],

            // TODO: sessionid는 예약 ID, username은 사용자 이름이나 아이디 사용
            // // Join form
            // mySessionId: "SessionA",
            // myUserName: "Participant" + Math.floor(Math.random() * 100),
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
        updateToken({ commit }, token) {
            commit('setToken', token);
        },

        // updateToken({ commit }, token) {
        //     commit('setToken', token);
        // },
        // removeToken({ commit }) {
        //     commit('clearToken');
        // }
    },
    getters: {
        // getProjectService: state => state.projectService,

    }
});
