<template lang="">
  <v-container>
    <v-row>
      <v-col cols="3"></v-col>
      <v-col>
        <h1>레시피 목록</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col> 정렬기준 </v-col>
    </v-row>
    <MoleculesRecipeCard
      v-for="(recipe, i) in RecipeList"
      :key="i"
      v-bind:recipe="recipe"
    ></MoleculesRecipeCard>
  </v-container>
</template>
<script>
import MoleculesRecipeCard from "../molecules/MoleculesRecipeCard.vue";

export default {
  components: {
    MoleculesRecipeCard,
  },
  data: function () {
    return {
      RecipeList: ["당근스프", "양송이스프", "옥수수스프"],
    };
  },
};
</script>
<style lang=""></style>
