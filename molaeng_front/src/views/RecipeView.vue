<template>
  <div>
    <recipe-detail-header v-bind:recipeInfo="recipeInfo" />
    <menu-tab :tabs="tabs" />
    <router-view></router-view>
    <under-bar-button :text="buttonText" @click.native="moveMolaeng" />
    <p>{{ recipeId }}</p>
  </div>
</template>

<script>
import RecipeDetailHeader from "../components/organisms/OrganismsRecipeDetailHeader.vue";
import UnderBarButton from "../components/atoms/AtomsUnderBarButton.vue";
import MenuTab from "../components/molecules/MoleculesMenuTab.vue";

import { mapGetters } from "vuex";

// recipeList에서 레시피아이디를 받아서 axios 요청 보내는 코드 추가해야 함
// 레시피아이디를 어떻게 받아오는지..?
export default {
  name: "RecipeView",
  components: {
    RecipeDetailHeader,
    UnderBarButton,
    MenuTab,
  },
  computed: {
    ...mapGetters(["recipeInfo"]),
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
          tabLink: "/",
        },
        {
          tabName: "리뷰",
          tabLink: "/recipe/" + this.recipeId + "/review",
        },
      ];
    },
  },
  mounted() {
    console.log("asdf" + this.recipeInfo);
    console.log(this.tabs);
  },
  created() {
    const pathName = this.$route.fullPath.split("/");
    console.log(pathName);
    this.recipeId = pathName[2];
    this.$store.dispatch("getRecipeInfo", this.recipeId);
  },
  data: () => ({
    recipeId: "",
    // recipeInfo: {
    //   recipeId: 1,
    //   recipeName: "동치미막국수",
    //   recipeKcal: 140,
    //   avgScore: 4.7,
    //   recipeImg: "http://file.okdab.com/UserFiles/searching/recipe/002400.jpg",
    //   isLiked: true,
    // },
    buttonText: "모랭일기에 기록하기",
  }),
  methods: {
    moveMolaeng() {
      this.$router.push({ path: "/diary" });
    },
  },
};
</script>
<style></style>
