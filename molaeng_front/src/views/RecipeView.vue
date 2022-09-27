<template>
  <div>
    <recipe-detail-header v-bind:recipeInfo="recipeInfo" />
    <v-tabs centered fixed-tabs>
      <menu-tab-item
        v-for="(title, index) in titles"
        :key="index"
        :text="title"
        :requestUrl="title"
      />
    </v-tabs>
    <!-- 임시 -->
    <button @click="moveReview">리뷰이동</button>
    <organisms-recipe-description-list />
    <under-bar-button :text="buttonText" @click.native="moveMolaeng" />
  </div>
</template>

<script>
import RecipeDetailHeader from "../components/organisms/OrganismsRecipeDetailHeader.vue";
import OrganismsRecipeDescriptionList from "@/components/organisms/OrganismsRecipeDescriptionList.vue";
import UnderBarButton from "../components/atoms/AtomsUnderBarButton.vue";
import MenuTabItem from "../components/atoms/AtomsMenuTabItem.vue";

export default {
  name: "RecipeView",
  components: {
    RecipeDetailHeader,
    OrganismsRecipeDescriptionList,
    UnderBarButton,
    MenuTabItem,
  },
  data: () => ({
    recipeInfo: {
      recipeId: 1,
      recipeName: "동치미막국수",
      recipeKcal: 140,
      avgScore: 4.7,
      recipeImg: "http://file.okdab.com/UserFiles/searching/recipe/002400.jpg",
      isLiked: true,
    },
    buttonText: "모랭일기에 기록하기",
    titles: ["재료", "조리방법", "가격정보", "리뷰"],
  }),
  methods: {
    moveMolaeng() {
      this.$router.push({ path: "/diary" });
    },
    moveReview() {
      this.$router.push({ path: "/review" });
    },
  },
};
</script>

<style></style>
