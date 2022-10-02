<template>
  <div>
    <recipe-detail-header-image :recipeInfo="recipeInfo" />
    <div style="position: sticky; top: 0; z-index: 3; background-color: white">
      <recipe-detail-header
        v-bind:recipeInfo="recipeInfo"
        :recipeId="recipeId"
      />
      <menu-tab :tabs="tabs" />
    </div>
    <router-view :recipeId="recipeId"></router-view>
  </div>
</template>

<script>
import RecipeDetailHeaderImage from "../components/atoms/AtomsRecipeDetailHeaderImage.vue";
import RecipeDetailHeader from "../components/organisms/OrganismsRecipeDetailHeader.vue";
import MenuTab from "../components/molecules/MoleculesMenuTab.vue";

import API from "@/api/APIs";
const api = API;

// recipeList에서 레시피아이디를 받아서 axios 요청 보내는 코드 추가해야 함
// 레시피아이디를 어떻게 받아오는지..?
export default {
  name: "RecipeView",
  components: {
    RecipeDetailHeaderImage,
    RecipeDetailHeader,
    MenuTab,
  },
  computed: {
    tabs() {
      return [
        {
          tabName: "재료",
          tabLink: "/recipe/" + this.recipeId + "/ingInfo",
        },
        {
          tabName: "조리방법",
          tabLink: "/recipe/" + this.recipeId + "/description",
        },
        {
          tabName: "가격정보",
          tabLink: "/recipe/" + this.recipeId + "/price",
        },
        {
          tabName: "리뷰",
          tabLink: "/recipe/" + this.recipeId + "/review",
        },
      ];
    },
  },
  async created() {
    //recipeId 저장
    const pathName = this.$route.fullPath.split("/");
    this.recipeId = pathName[2];
    //레시피 상단바 상세정보
    this.recipeInfo = await api.getRecipeInfo(this.recipeId);
  },
  data: () => ({
    recipeId: "",
    //레시피 상단바 상세정보
    recipeInfo: {},
  }),
};
</script>
<style></style>
