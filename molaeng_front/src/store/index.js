import Vue from "vue";
import Vuex from "vuex";

import { ingredient } from "@/store/ingredient";
import { user } from "@/store/user";

Vue.use(Vuex);
import createPersistedState from "vuex-persistedstate";

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    ingredient,
    user,
  },
  plugins: [
    createPersistedState({
      paths: ["ingredient"],
      storage: window.sessionStorage,
    }),
  ],
});
