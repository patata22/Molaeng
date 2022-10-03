<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import API from "@/api/APIs";

const api = API;

export default {
  name: "SearchView",
  components: {},
  computed: {
    ...mapGetters(["flag", "selectedIngredients"]),
  },
  data: () => ({
    buttonText: "레시피 검색",
    tabs: [
      {
        tabName: "재료",
        tabLink: "/search",
      },
      {
        tabName: "레시피",
        tabLink: "/search/recipe",
      },
    ],
  }),
  async created() {
    if (!this.flag) {
      let result = await api.getAllIngredients();
      for (let i = 0; i < result.ingredientList.length; i++) {
        let ingredient = result.ingredientList[i];
        for (let j of this.selectedIngredients) {
          if (ingredient.ingredientId == j.ingredientId) {
            ingredient.selected = true;
          }
        }
        this.$store.commit("INIT_INGREDIENT", ingredient);
      }
      this.$store.commit("TOGGLE_FLAG");
    }
  },
};
</script>

<style>
#content {
  padding-bottom: 52px;
  overflow: auto;
}
</style>
