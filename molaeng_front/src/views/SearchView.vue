<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import API from "@/api/APIs";

const api = API;

export default {
  name: "SearchView",
  components: {},
  data: () => ({
    buttonText: "레시피 검색",
    tabs: [
      {
        tabName: "재료",
        tabLink: "/search/ingredient",
      },
      {
        tabName: "레시피",
        tabLink: "/search/recipe",
      },
    ],
  }),
  async created() {
    this.$store.commit("INIT_INGREDIENT");
    let result = await api.getAllIngredients();
    for (let i = 0; i < result.ingredientList.length; i++) {
      let ingredient = result.ingredientList[i];
      this.$store.commit("ADD_INGREDIENT", ingredient);
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
