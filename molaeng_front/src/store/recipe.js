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
      //열량 정보가 0인 경우 -로 표시?
    },
    GET_RECIPE_DETAIL(state, payload) {
      state.recipeDetailList = payload.result;
      //레시피 조리 순서가 섞여 있을 때 순서 오름차순으로 정렬 후 저장
      state.recipeDetailList.sort(function (a, b) {
        return a.recipeOrder - b.recipeOrder;
      });
    },
  },
  actions: {
    //레시피 상세정보 상단바 정보
    getRecipeInfo({ commit }, recipeId) {
      //   userId 구하기 - http 헤더..?나중에 질문해보자..

      const API_URL = api.recipeInfo.getRecipeInfo() + "/" + recipeId;
      axios({
        methods: "GET",
        params: {
          userId: 1,
        },
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
    //관심레시피 등록
    registRecipeLike(context, recipeId) {
      context;
      //userId 구하기
      let userId = 1;
      const API_URL = api.recipeInfo.registRecipeLike();
      console.log(API_URL);
      axios
        .post(API_URL, {
          userId: userId,
          recipeId: recipeId,
        })
        .then(() => {})
        .catch((err) => console.log("관심 레시피 등록 오류 : " + err));
    },
    //관심레시피 삭제
    deleteRecipeLike(context, recipeId) {
      context;
      //userId 구하기
      let userId = 1;
      const API_URL = api.recipeInfo.deleteRecipeLike();
      console.log(API_URL);
      axios
        .delete(API_URL, {
          data: {
            userId: userId,
            recipeId: recipeId,
          },
        })
        .then(() => {})
        .catch((err) => console.log("관심 레시피 삭제 오류 : " + err));
    },
  },
  modules: {},
};
