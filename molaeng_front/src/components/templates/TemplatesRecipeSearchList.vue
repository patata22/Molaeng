<template lang="">
  <v-container>
    <div class="d-flex justify-center align center pt-10">
      <h1 class="dark--text">레시피 목록</h1>
    </div>
    <organisms-recipe-card
      v-for="(recipe, i) in recipeList"
      :key="i"
      :recipe="recipe"
      class="mx-auto mt-10"
    >
    </organisms-recipe-card>
    <infinite-loading @infinite="getRecipeByName">
      <div slot="no-more"></div>
    </infinite-loading>
    <br />
  </v-container>
</template>
<script>
// import { mapState } from "vuex";
import axios from "axios";
import OrganismsRecipeCard from "../organisms/OrganismsRecipeCard.vue";
import InfiniteLoading from "vue-infinite-loading";
export default {
  name: "RecipeSearchList",
  created() {
    // this.getRecipeByIngredient();
  },
  data: function () {
    return {
      page: 0,
      size: 5,
      hasNext: true,
      recipeList: [],
    };
  },
  computed: {
    //   ...mapState({
    //     ingredientList(state) {
    //       var ingredient = [];
    //       var temp = state.ingredient.selectedIngredients;
    //       temp.forEach((e) => {
    //         ingredient.push({
    //           ingredientId: e.ingredientId,
    //           ingredientName: e.ingredientName,
    //         });
    //       });
    //       return ingredient;
    //     },
    //   }),
  },
  components: {
    OrganismsRecipeCard,
    InfiniteLoading,
  },
  methods: {
    // userId 수정!
    getRecipeByName($state) {
      var temp = this;
      var keyWord = window.location.pathname.split("/")[2];
      axios
        .get(
          "https://j7a604.p.ssafy.io/molaeng/search/name/" +
            keyWord +
            "?size=5&userId=1&page=" +
            this.page
        )
        .then((response) => {
          response.data.result.recipeList.forEach((e) => {
            temp.recipeList.push(e);
          });
          temp.page += 1;
          if (response.data.result.hasNext) {
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>
<style lang=""></style>
