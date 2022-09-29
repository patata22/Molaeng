import api from "@/api/RestAPI";
import axios from "axios";

import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const recipe = {
  state: {
    recipeInfo: {}, //레시피 상단바 정보 (레시피명, 열량, 대표이미지, 찜여부)
    recipeDetail: [], //조리방법 배열 (순서, 이미지, 설명)
  },
  getters: {
    recipeInfo: (state) => state.recipeInfo,
    recipeDetail: (state) => state.recipeDetail,
  },
  mutations: {
    GET_RECIPE_INFO(state, payload) {
      console.log(payload.result);
      state.recipeInfo = payload.result;
      console.log(state.recipeInfo);
    },
    GET_RECIPE_DETAIL(state, payload) {
      state.recipeDetail = payload.result;
    },
  },
  actions: {
    //레시피 상세정보 상단바 정보
    getRecipeInfo({ commit }, recipeId) {
      // const API_URL = api + "/recipe/" + recipeId;
      const API_URL = `http://j7a604.p.ssafy.io:8080/molaeng/recipe/${recipeId}`;
      console.log(API_URL);
      axios({
        methods: "GET",
        url: API_URL,
      })
        .then((res) => {
          commit("GET_RECIPE_INFO", res.data);
          console.log(res.data);
        })
        .catch((err) => console.log("상단바 정보 오류 : " + err));
    },
    //레시피 조리방법
    getRecipeDetail({ commit }, recipeId) {
      // const API_URL = api + "/recipe/detail/" + recipeId;
      const API_URL = `${api}/recipe/detail/${recipeId}`;
      axios({
        methods: "GET",
        url: API_URL,
      })
        .then((res) => {
          commit("GET_RECIPE_DETAIL", res.data);
        })
        .catch((err) => console.log("조리방법 정보 오류 : " + err));
    },
  },
  modules: {},
};
