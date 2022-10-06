import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const user = {
  state: {
    userInfo: {},
    gugunList: [
      { name: "강남구", value: 1 },
      { name: "강동구", value: 2 },
      { name: "강북구", value: 3 },
      { name: "강서구", value: 4 },
      { name: "관악구", value: 5 },
      { name: "광진구", value: 6 },
      { name: "구로구", value: 7 },
      { name: "금천구", value: 8 },
      { name: "노원구", value: 9 },
      { name: "도봉구", value: 10 },
      { name: "동대문구", value: 11 },
      { name: "동작구", value: 12 },
      { name: "마포구", value: 13 },
      { name: "서대문구", value: 14 },
      { name: "서초구", value: 15 },
      { name: "성동구", value: 16 },
      { name: "성북구", value: 17 },
      { name: "송파구", value: 18 },
      { name: "양천구", value: 19 },
      { name: "영등포구", value: 20 },
      { name: "용산구", value: 21 },
      { name: "은평구", value: 22 },
      { name: "종로구", value: 23 },
      { name: "중구", value: 24 },
      { name: "중랑구", value: 25 },
    ],
  }, //원본 소스. vue에서 data로 불러올 수 있음
  getters: {
    getSido: (state) => {
      return state.Sido;
    },
    getGugunList: (state) => {
      return state.gugunList;
    },
  }, // state를 접근할 땐 getter를 사용해야.
  mutations: {}, // setter. state를 변경할 땐 mutations를 사용해야. 무조건 동기
  actions: {}, // 비동기
  modules: {},
};
