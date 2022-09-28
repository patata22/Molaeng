<template lang="">
  <div>
    <h1>검색</h1>
    <v-input
      ><v-text-field
        v-model="keyWord"
        @input="matchingKeyword"
        id="searchInputText"
        class="font-weight-bold"
      ></v-text-field
    ></v-input>
    <v-card
      v-for="(recipe, i) in matchList"
      :key="i"
      @click="moveToRecipe(recipe.recipeId)"
      class="font-weight-bold dark--text"
    >
      <!-- {{ recipe.recipeName }} -->
      {{ recipe.F }}
      <span class="primary--text">{{ recipe.M }}</span
      >{{ recipe.E }}
    </v-card>
  </div>
</template>
<script>
import axios from "axios";
export default {
  created() {
    this.getAllRecipe();
  },
  data: function () {
    return {
      recipeNameList: [],
      recipeMap: new Map(),
      keyWord: "",
      matchList: [],
    };
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
