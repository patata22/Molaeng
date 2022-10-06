<template lang="">
  <v-card style="margin: 0">
    <div
      class="d-flex justify-space-between align-center"
      style="background: #fef3c6; height: 30px; max-width: 420px"
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
      <v-col cols="7" align="left">
        <v-chip
          v-for="(ingredient, i) in recipe.ingredientList"
          :key="i"
          outlined
          color="dark"
          class="mr-1 mt-1"
        >
          {{ ingredient.ingredientName }}
        </v-chip>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-spacer v-on:click="moveToRecipe(recipe.recipeId)" />
      <v-col v-on:click="moveToRecipe(recipe.recipeId)" cols="auto">
        <span class="semidark--text"
          >열량 {{ recipe.recipeKcal }}Kcal
        </span></v-col
      >
      <v-col cols="auto" class="ml-10 mr-5">
        <v-icon
          v-if="this.recipe.isLiked == 0"
          color="carrot"
          v-on:click="likeRecipe()"
          >mdi-heart-outline
        </v-icon>
        <v-icon v-else color="carrot" v-on:click="dislikeRecipe()"
          >mdi-heart</v-icon
        >
      </v-col>
    </v-row>
  </v-card>
</template>
<script>
import API from "@/api/APIs";

const api = API;

export default {
  name: "RecipeCard",
  data: function () {
    return {
      userId: 1,
    };
  },
  props: {
    recipe: Object,
  },
  created() {
    this.getUserIdByCookie();
  },
  mounted() {
    this.isLiked = this.recipe.isLiked;
  },
  methods: {
    async likeRecipe() {
      await api.recipeLike(this.recipe.recipeId, this.userId);
      this.$router.go();
    },
    async dislikeRecipe() {
      await api.dislikeRecipe(this.recipe.recipeId, this.userId);
      this.$router.go();
    },
    moveToRecipe(i) {
      this.$router.push("/recipe/" + i);
    },
    getUserIdByCookie() {
      let userId = this.$cookies.get("userId");
      console.log(userId);
      if (userId) {
        this.userId = parseInt(userId);
      }
    },
  },
};
</script>
<style></style>
