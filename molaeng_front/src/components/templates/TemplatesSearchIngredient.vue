<template>
  <div>
    <div id="content">
      <atoms-search-box v-on:change="changeKeyword" :searchword="searchword" />
      <menu-tab :tabs="tabs" />
      <selected-tag-list />
      <ingredient-card-list :keyWord="keyWord" />
    </div>
    <under-bar-button :text="buttonText" :to="buttonLink" />
  </div>
</template>

<script>
import SelectedTagList from "@/components/molecules/MoleculesSelectedTagList.vue";
import IngredientCardList from "@/components/templates/TemplatesIngredientCardList.vue";
import UnderBarButton from "@/components/atoms/AtomsUnderBarButton.vue";
import AtomsSearchBox from "../atoms/AtomsSearchBox.vue";
import MenuTab from "../molecules/MoleculesMenuTab.vue";
export default {
  name: "SearchIngredient",
  components: {
    AtomsSearchBox,
    MenuTab,
    SelectedTagList,
    IngredientCardList,
    UnderBarButton,
  },
  data: () => ({
    buttonText: "레시피 찾기",
    buttonLink: "/recipeList",
    recipeNameList: [],
    recipeMap: new Map(),
    matchList: [],
    keyWord: "",
    tabs: [
      {
        tabName: "재료",
        tabLink: "/search",
      },
      {
        tabName: "레시피",
        tabLink: "/search/recipe",
      },
    ],
    searchword: "재료를 검색해주세요.",
  }),
  methods: {
    changeKeyword(value) {
      this.keyWord = value;
    },
  },
};
</script>

<style></style>
