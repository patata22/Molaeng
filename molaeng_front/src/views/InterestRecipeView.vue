<template>
  <div>
    <interest-recipe-header
      class="mx-auto"
      style="max-width: 420px"
      :tabs="tabs"
    />
    <router-view></router-view>
  </div>
</template>

<script>
import InterestRecipeHeader from "@/components/organisms/OrganismInterestRecipeHeader.vue";

export default {
  name: "InterestRecipe",
  components: {
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
