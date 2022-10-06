<template lang="">
  <v-card class="mx-auto mt-10">
    <div
      class="d-flex justify-space-between align-center"
      style="background: #fef3c6; height: 30px"
      v-on:click="moveToRecipe(recipe.recipeId)"
    >
      <v-rating
        small
        :value="Number(recipe.avgScore.toFixed())"
        readonly
        background-color="carrot"
        color="carrot"
        class="ml-3 mt-2"
      ></v-rating>
      <span class="carrot--text mr-3 mt-2 text-weight-bold text-subtitle-2"
        >{{ recipe.cost.toFixed() }}원 더 필요해요!</span
      >
      <!-- <h3>{{ recipe.recipeName }}</h3> -->
    </div>
    <div
      class="d-flex justify-start align-center"
      style="background: #fef3c6; height: 40px"
      v-on:click="moveToRecipe(recipe.recipeId)"
    >
      <h3 class="ml-3">
        <span class="dark--text">{{ recipe.recipeName }}</span>
      </h3>
    </div>
    <v-row
      class="pt-1"
      align="center"
      v-on:click="moveToRecipe(recipe.recipeId)"
    >
      <v-col cols="5">
        <v-img :src="recipe.recipeImage" class="ml-1" max-> </v-img>
      </v-col>
      <v-col cols="7">
        <v-chip
          v-for="(ingredient, i) in recipe.ingredientList"
          :key="i"
          outlined
          :color="haveIngredient(ingredient.ingredientId)"
          class="mr-1 mt-1"
        >
          {{ ingredient.ingredientName }}
        </v-chip>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-spacer v-on:click="moveToRecipe(recipe.recipeId)" />
      <v-col cols="5" />
      <v-col cols="7" class="pt-0">
        <span v-on:click="moveToRecipe(recipe.recipeId)" class="semidark--text"
          >열량 {{ recipe.recipeKcal }}Kcal
        </span>
        <span style="float: right" class="mr-3">
          <v-icon
            v-if="recipe.isLiked == 0"
            color="carrot"
            v-on:click="likeRecipe"
            >mdi-heart-outline
          </v-icon>
          <v-icon v-else color="carrot" v-on:click="dislikeRecipe"
            >mdi-heart</v-icon
          >
        </span>
      </v-col>
    </v-row>
    <v-snackbar
      color="carrot"
      rounded="pill"
      text
      centered
      v-model="snackbar"
      timeout="1500"
    >
      로그인이 필요한 기능입니다!
    </v-snackbar>
  </v-card>
</template>
<script>
import { mapState } from "vuex";
import API from "@/api/APIs";
export default {
  name: "OrganismsRecipeCard",
  computed: {
    ...mapState({
      ingredient(state) {
        var ing = [];
        var temp = state.ingredient.selectedIngredients;
        temp.forEach((e) => {
          ing.push(e.ingredientId);
        });
        return ing;
      },
    }),
  },
  props: { recipe: Object },
  data: function () {
    return {
      snackbar: false,
    };
  },
  methods: {
    haveIngredient(i) {
      if (this.ingredient.indexOf(i) < 0) return "dark";
      return "carrot";
    },
    likeRecipe() {
      var temp = this;
      if (!temp.$cookies.get("userId")) {
        temp.snackbar = true;
      } else {
        API.registRecipeLike(temp.recipe.recipeId, temp.$cookies.get("userId"))
          .then(() => {
            temp.recipe.isLiked = 1;
          })
          .catch((error) => console.log(error));
      }
    },
    dislikeRecipe() {
      var temp = this;
      API.dislikeRecipe(temp.recipe.recipeId, temp.$cookies.get("userId"))
        .then((response) => {
          console.log(response);
          temp.recipe.isLiked = 0;
        })
        .catch((error) => console.log(error));
    },
    moveToRecipe(i) {
      this.$router.push("/recipe/" + i);
    },
  },
};
</script>
<style lang=""></style>
