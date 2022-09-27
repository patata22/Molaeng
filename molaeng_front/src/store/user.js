// import axios from "axios";
// import api from "@/api/RestAPI";
// import router from "@/router";

// // Vue.use(Vuex);

// export default {
//   state: {
//     gugun: [
//       "강남구",
//       "강동구",
//       "강북구",
//       "강서구",
//       "관악구",
//       "광진구",
//       "구로구",
//       "금천구",
//       "노원구",
//       "도봉구",
//       "동대문구",
//       "동작구",
//       "마포구",
//       "서대문구",
//       "서초구",
//       "성동구",
//       "성북구",
//       "송파구",
//       "양천구",
//       "영등포구",
//       "용산구",
//       "은평구",
//       "종로구",
//       "중구",
//       "중랑구",
//     ],
//   }, //원본 소스. vue에서 data로 불러올 수 있음
//   getters: {
//     userId: (state) => state.userId,
//     nickname: (state) => state.nickname,
//     myRegion: (state) => state.myRegion,
//     uuid: (state) => state.uuid,
//     // gugun: (state) => state.gugun,
//     getAllGugun: (state) => {
//       return state.gugun;
//     },
//   }, // state를 접근할 땐 getter를 사용해야.
//   mutations: {
//     SET_NICKNAME: (state, nickname) => (state.nickname = nickname),
//     SET_MYREGION: (state, myRegion) => (state.myRegion = myRegion),
//   }, // setter. state를 변경할 땐 mutations를 사용해야. 무조건 동기
//   actions: {
//     getUserInfo({ commit }, userId) {
//       axios({
//         methods: "post",
//         url: api.userInfo.getUserInfo(userId),
//       })
//         .then((res) => {
//           console.log(res.data);
//           commit("SET_NICKNAME", res.data.nickname);
//           commit("SET_MYREGION", res.data.myRegion);
//           console.log("post 요청 들어왔다~~~");
//         })
//         .catch((err) => consol.log("정보 불러오기 오류오류"));
//     },
//   }, // 비동기
//   modules: {},
// };
