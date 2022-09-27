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
        ingredientTitle: "ㄴ",
        ingredientList: [
          { ingredientId: 9, ingredientName: "넙치", selected: false },
          { ingredientId: 10, ingredientName: "냉이", selected: false },
        ],
      },
      {
        ingredientTitle: "ㄷ/ㄸ",
        ingredientList: [
          { ingredientId: 12, ingredientName: "당근", selected: false },
          { ingredientId: 15, ingredientName: "대추", selected: false },
        ],
      },
    ],
    selectedIngredients: [],
  }, //원본 소스. vue에서 data로 불러올 수 있음
  getters: {
    allIngredients: (state) => {
      return state.ingredients;
    },
    // 선택된 재료들을 불러오는 함수
    selectedIngredients: (state) => {
      return state.selectedIngredients;
    },
    ingredientById: (state) => (id) => {
      return state.ingredients.find(
        (ingredient) => ingredient.ingredientId === id
      );
    },
  }, // state를 접근할 땐 getter를 사용해야.
  mutations: {
    SET_INGREDIENT_SELECT(state, ingredient) {
      ingredient.selected = !ingredient.selected;
    },
    ADD_CART(state, ingredient) {
      ingredient.selected = true;
      state.selectedIngredients.push(ingredient);
    },
    REMOVE_CART(state, ingredient) {
      let idx = state.selectedIngredients.findIndex((i) => {
        return i.ingredientId === ingredient.ingredientId;
      });
      ingredient.selected = false;
      state.selectedIngredients.splice(idx, 1);
    },
  }, // setter. state를 변경할 땐 mutations를 사용해야. 무조건 동기
  actions: {}, // 비동기
  modules: {},
};
