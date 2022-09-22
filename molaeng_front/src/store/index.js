import Vue from "vue";
import Vuex from "vuex";

import { ingredient } from "../store/ingredient";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    ingredient,
  },
});
