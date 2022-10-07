<template lang="">
  <div class="mx-auto" style="max-width: 420px">
    <div class="searchBarBackground">
      <atoms-search-box
        v-on:change="changeKeyword"
        :searchword="searchword"
        v-on:submit="moveToList"
        class="searchBox"
      >
      </atoms-search-box>
    </div>
    <menu-tab :tabs="tabs"></menu-tab>
    <v-card
      flat
      v-for="(recipe, i) in matchList"
      :key="i"
      @click="moveToRecipe(recipe.recipeId)"
      height="50px"
      class="font-weight-bold dark--text mt-0 ml-5 pt-5"
    >
      <v-icon>mdi-magnify</v-icon>
      {{ recipe.F }}<span class="primary--text">{{ recipe.M }}</span
      >{{ recipe.E }}
    </v-card>
    <under-bar-button :text="buttonText" :to="buttonLink + keyWord" />
  </div>
</template>
<script>
import AtomsSearchBox from "../atoms/AtomsSearchBox.vue";
import axios from "axios";
import MenuTab from "../molecules/MoleculesMenuTab.vue";
import UnderBarButton from "@/components/atoms/AtomsUnderBarButton.vue";
export default {
  name: "OrganismsSearchRecipe",
  created() {
    this.getAllRecipe();
  },
  data: function () {
    return {
      recipeNameList: [],
      matchList: [],
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
      searchword: "레시피를 검색해주세요",
      buttonText: "레시피 찾기",
      buttonLink: "/recipeSearchList/",
      keyWord: "",
    };
  },
  components: {
    AtomsSearchBox,
    MenuTab,
    UnderBarButton,
  },
  methods: {
    getAllRecipe() {
      var temp = this;
      axios
        .get("https://j7a604.p.ssafy.io/molaeng/search/all")
        .then((response) => {
          response.data.result.forEach((dto) => {
            temp.recipeNameList.push({
              recipeName: dto.recipeName,
              recipeId: dto.recipeId,
            });
          });
        })
        .catch();
    },
    matchingKeyword() {
      var temp = this;
      temp.matchList = [];
      if (temp.keyWord == "") return;
      temp.recipeNameList.forEach((recipe) => {
        if (recipe.recipeName.indexOf(this.keyWord) >= 0) {
          var idx = recipe.recipeName.indexOf(this.keyWord);
          temp.matchList.push({
            recipeName: recipe.recipeName,
            recipeId: recipe.recipeId,
            F: recipe.recipeName.substr(0, idx),
            M: recipe.recipeName.substr(idx, temp.keyWord.length),
            E: recipe.recipeName.substr(idx + temp.keyWord.length),
          });
        }
      });
    },
    changeKeyword(value) {
      this.keyWord = value;
      this.matchingKeyword();
    },
    moveToRecipe(i) {
      this.$router.push("/recipe/" + i);
    },
    moveToList() {
      this.$router.push("/recipeSearchList/" + this.keyWord);
    },
  },
};
</script>
<style>
#searchInputText {
  color: #5b574b;
}
</style>
