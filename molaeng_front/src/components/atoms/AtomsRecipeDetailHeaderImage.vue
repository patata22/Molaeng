<template>
  <v-img
    class="recipeImage"
    :lazy-src="recipeInfo.recipeImg"
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

<style scoped>
.recipeImage {
  max-width: 70%;
  margin-left: 15%;
  margin-right: 15%;
}
</style>
