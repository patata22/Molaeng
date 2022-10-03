// import axios from "axios";
import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

function getGugunList() {
    const gugun = {
        1: "강남구",
        2: "강동구",
        3: "강북구",
        4: "강서구",
        5: "관악구",
        6: "광진구",
        7: "구로구",
        8: "금천구",
        9: "노원구",
        10: "도봉구",
        11: "동대문구",
        12: "동작구",
        13: "마포구",
        14: "서대문구",
        15: "서초구",
        16: "성동구",
        17: "성북구",
        18: "송파구",
        19: "양천구",
        20: "영등포구",
        21: "용산구",
        22: "은평구",
        23: "종로구",
        24: "중구",
        25: "중랑구",
    };
    return gugun;
}

export const user = {
    state: {
        Sido: "서울특별시",
        gugun: {},
    }, //원본 소스. vue에서 data로 불러올 수 있음
    getters: {
        getSido: (state) => {
            return state.Sido;
        },
        getGugunList: (state) => {
            return state.gugun;
        }
    }, // state를 접근할 땐 getter를 사용해야.
    mutations: {
        SET_GUGUN(state, user) {
            gugun = getGugunList();
        }
    }, // setter. state를 변경할 땐 mutations를 사용해야. 무조건 동기
    actions: {
        // getMyRegion({ commit }, gugun) {

        // },
    }, // 비동기
    modules: {},
};
