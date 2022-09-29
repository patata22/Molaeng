<template lang="">
  <div>
    <atoms-search-box v-on:change="changeKeyword"> </atoms-search-box>
    <menu-tab :tabs="tabs"></menu-tab>
    <!-- <v-input
      ><v-text-field
        v-model="keyWord"
        @input="matchingKeyword"
        id="searchInputText"
        class="font-weight-bold"
      ></v-text-field
    ></v-input> -->
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
  </div>
</template>
<script>
import AtomsSearchBox from "../atoms/AtomsSearchBox.vue";
import axios from "axios";
import MenuTab from "../molecules/MoleculesMenuTab.vue";
export default {
  name: "OrganismsSearchRecipe",
  created() {
    this.getAllRecipe();
  },
  data: function () {
    return {
      recipeNameList: [],
      recipeMap: new Map(),
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
    };
  },
  components: {
    AtomsSearchBox,
    MenuTab,
  },
  methods: {
    getAllRecipe() {
      var temp = this;
      axios
        .get("http://localhost:8080/molaeng/search/all")
        .then((response) => {
          response.data.result.forEach((dto) => {
            temp.recipeNameList.push(dto.recipeName);
            temp.recipeMap.set(dto.recipeName, dto.recipeId);
          });
        })
        .catch((error) => console.log(error));
    },
    matchingKeyword() {
      var temp = this;
      temp.matchList = [];
      if (temp.keyWord == "") return;
      temp.recipeNameList.forEach((name) => {
        if (name.indexOf(this.keyWord) >= 0) {
          var idx = name.indexOf(this.keyWord);
          temp.matchList.push({
            recipeName: name,
            recipeId: temp.recipeMap.get(name),
            F: name.substr(0, idx),
            M: name.substr(idx, temp.keyWord.length),
            E: name.substr(idx + temp.keyWord.length),
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
  },
};
</script>
<style>
#searchInputText {
  color: #5b574b;
}
</style>
