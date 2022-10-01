<template>
  <div>
    <nav-bar></nav-bar>
    <interest-recipe-header :tabs="tabs" />
    <router-view></router-view>
  </div>
</template>

<script>
import InterestRecipeHeader from "../components/organisms/OrganismInterestRecipeHeader.vue";
import NavBar from "../components/molecules/MoleculesNavBar.vue";

export default {
  name: "InterestRecipe",
  components: {
    NavBar,
    InterestRecipeHeader,
  },
  data: () => ({
    buttonText: "관심 레시피",
    tabs: [
      {
        tabName: "찜한 레시피",
        tabLink: "/interestRecipe/recipeLike",
      },
      {
        tabName: "최근 본 레시피",
        tabLink: "/interestRecipe/recipeHistory",
      },
    ],
  }),
};
</script>
