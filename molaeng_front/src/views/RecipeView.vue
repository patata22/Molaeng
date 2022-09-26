<template>
  <div>
    <recipe-detail-header v-bind:recipeInfo="recipeInfo" />
    <organisms-recipe-description-list />
    <under-bar-button :text="buttonText" />
  </div>
</template>

<script>
import RecipeDetailHeader from "../components/organisms/OrganismsRecipeDetailHeader.vue";
import OrganismsRecipeDescriptionList from "@/components/organisms/OrganismsRecipeDescriptionList.vue";
import UnderBarButton from "../components/atoms/AtomsUnderBarButton.vue";

export default {
  name: "RecipeView",
  components: {
    RecipeDetailHeader,
    OrganismsRecipeDescriptionList,
    UnderBarButton,
  },
  data: () => ({
    recipeInfo: {
      recipeId: 1,
      recipeName: "새우두부계란찜",
      recipeKcal: 140,
      avgScore: 4.7,
      recipeImg:
        "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png",
      isLiked: true,
    },
    buttonText: "모랭일기에 기록하기",
  }),
};
</script>

<style></style>
