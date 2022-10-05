<template lang="">
  <v-container>
    <div class="d-flex justify-center align center">
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
    <v-btn fab fixed right bottom color="carrot" @click="toTop">
      <v-icon color="white">mdi-arrow-up-thick</v-icon>
    </v-btn>
  </v-container>
</template>
<script>
import axios from "axios";
import OrganismsRecipeCard from "../organisms/OrganismsRecipeCard.vue";
import InfiniteLoading from "vue-infinite-loading";
export default {
  name: "RecipeSearchList",
  data: function () {
    return {
      page: 0,
      size: 5,
      hasNext: true,
      recipeList: [],
    };
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
      if (keyWord == "") {
        axios
          .get(
            "https://j7a604.p.ssafy.io/molaeng/search/name?size=5&userId=1&page=" +
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
          });
      } else {
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
      }
    },
    toTop() {
      window.scrollTo({ top: 0, left: 0, behavior: "smooth" });
    },
  },
};
</script>
<style lang=""></style>
