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
    <router-view
      :recipeId="recipeId"
      :outeat="outeat"
      :recipePrice="recipePrice"
    ></router-view>
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
  data: () => ({
    recipeId: "",
    //레시피 상단바 상세정보
    recipeInfo: {},
    outeat: {
      seoul: 0,
      my: 0,
      gugunName: "",
    },
    recipePrice: 5000,
  }),
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
    compareLocation() {
      if (this.outeat.my > 0) {
        return this.outeat.gugunName;
      } else return "서울";
    },
    comparePrice() {
      if (this.compareLocation == "서울") {
        return this.outeat.seoul - this.recipePrice;
      } else return this.outeat.my - this.recipePrice;
    },
  },
  async created() {
    //recipeId 저장
    const pathName = this.$route.fullPath.split("/");
    this.recipeId = pathName[2];
    //레시피 상단바 상세정보
    this.recipeInfo = await api.getRecipeInfo(this.recipeId);
    this.outeat = await api.outPrice(this.recipeId);

    this.addLocalStorage();
  },

  methods: {
    addLocalStorage() {
      let newList = [];
      let recentList = JSON.parse(localStorage.getItem("recentRecipe"));
      if (recentList) {
        for (let item of recentList) {
          if (!(item == this.recipeId)) {
            newList.push(item);
          }
        }
      }
      newList.unshift(this.recipeId);
      if (newList.length > 5) newList.pop();
      localStorage.setItem("recentRecipe", JSON.stringify(newList));
    },
  },
};
</script>
<style></style>
