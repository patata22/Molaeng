import api from "@/api/RestAPI";
import axios from "axios";

import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const recipe = {
  state: {
    recipeInfo: {}, //레시피 상단바 정보 (레시피명, 열량, 대표이미지, 찜여부)
    recipeDetailList: [], //조리방법 배열 (순서, 이미지, 설명)
  },
  getters: {
    recipeInfo: (state) => state.recipeInfo,
    recipeDetailList: (state) => state.recipeDetailList,
  },
  mutations: {
    GET_RECIPE_INFO(state, payload) {
      state.recipeInfo = payload.result;
    },
    GET_RECIPE_DETAIL(state, payload) {
      state.recipeDetailList = payload.result;
    },
  },
  actions: {
    //레시피 상세정보 상단바 정보
    getRecipeInfo({ commit }, recipeId) {
      const API_URL = api.recipeInfo.getRecipeInfo() + "/" + recipeId;
      axios({
        methods: "GET",
        url: API_URL,
      })
        .then((res) => {
          commit("GET_RECIPE_INFO", res.data);
        })
        .catch((err) => console.log("상단바 정보 오류 : " + err));
    },
    //레시피 조리방법
    getRecipeDetail({ commit }, recipeId) {
      const API_URL = api.recipeInfo.getRecipeDetail() + "/" + recipeId;
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
