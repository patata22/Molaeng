import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const ingredient = {
  state: {
    ingredients: [
      {
        ingredientTitle: "ㄱ/ㄲ",
        ingredientList: [
          { ingredientId: 7, ingredientName: "고등어", selected: false },
          { ingredientId: 8, ingredientName: "가지", selected: false },
        ],
      },
      {
        ingredientTitle: "ㄷ/ㄸ",
        ingredientList: [
          { ingredientId: 10, ingredientName: "당근", selected: false },
          { ingredientId: 15, ingredientName: "대추", selected: false },
        ],
      },
    ],
  }, //원본 소스. vue에서 data로 불러올 수 있음
  getters: {
    getAllIngredients: (state) => {
      return state.ingredients;
    },
    // 선택된 재료들을 불러오는 함수
    getSelectedIngredients: (state) => {
      return state.ingredients.filter((ingredient) => ingredient.seleted);
    },
    getIngredientById: (state) => (id) => {
      return state.ingredients.find(
        (ingredient) => ingredient.ingredientId === id
      );
    },
  }, // state를 접근할 땐 getter를 사용해야.
  mutations: {
    SET_INGREDIENT_SELECT(state, ingredient) {
      ingredient.selected = !ingredient.selected;
    },
  }, // setter. state를 변경할 땐 mutations를 사용해야. 무조건 동기
  actions: {}, // 비동기
  modules: {},
};
