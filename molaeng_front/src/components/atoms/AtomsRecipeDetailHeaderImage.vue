<template>
  <v-img
    :lazy-src="recipeInfo.recipeImg"
    height="auto"
    width="100%"
    :src="recipeInfo.recipeImg"
  ></v-img>
</template>

<script>
export default {
  name: "RecipeDetailHeaderImage",
  props: {
    recipeInfo: Object,
  },
};
</script>

<style></style>
