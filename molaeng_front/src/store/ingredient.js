import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

function getFirstChar(kor) {
  const f = [
    "ㄱ",
    "ㄲ",
    "ㄴ",
    "ㄷ",
    "ㄸ",
    "ㄹ",
    "ㅁ",
    "ㅂ",
    "ㅃ",
    "ㅅ",
    "ㅆ",
    "ㅇ",
    "ㅈ",
    "ㅉ",
    "ㅊ",
    "ㅋ",
    "ㅌ",
    "ㅍ",
    "ㅎ",
  ];
  // const s = ['ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ',
  //            'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ',
  //            'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'];
  // const t = ['', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ',
  //            'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ',
  //            'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ',
  //            'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'];

  const ga = 44032;
  let uni = kor.charCodeAt(0);

  uni = uni - ga;

  let fn = parseInt(uni / 588);

  return f[fn];
}

export const ingredient = {
  state: {
    ingredients: [
      {
        ingredientTitle: "ㄱ/ㄲ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㄴ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㄷ/ㄸ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㄹ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅁ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅂ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅅ/ㅆ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅇ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅈ/ㅉ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅋ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅌ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅍ",
        ingredientList: [],
      },
      {
        ingredientTitle: "ㅎ",
        ingredientList: [],
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
    ADD_INGREDIENT(state, ingredient) {
      let start = getFirstChar(ingredient.ingredientName.substring(0, 1));
      for (let ing of state.ingredients) {
        if (ing.ingredientTitle.includes(start)) {
          ing.ingredientList.push(ingredient);
        }
      }
    },
  }, // setter. state를 변경할 땐 mutations를 사용해야. 무조건 동기
  actions: {}, // 비동기
  modules: {},
};
