<template>
  <div>
    <v-img
      :lazy-src="recipeInfo.recipeImg"
      height="auto"
      width="100%"
      :src="recipeInfo.recipeImg"
    ></v-img>
    <div v-if="recipeInfo.isLiked">
      <v-icon large color="primary">mdi-cards-heart</v-icon>
    </div>
    <div v-else>
      <v-icon large color="primary">mdi-cards-heart-outline</v-icon>
    </div>
    <div class="recipeName">{{ recipeInfo.recipeName }}</div>
    <div class="recipeKcal">열량 {{ recipeInfo.recipeKcal }}kcal</div>
  </div>
</template>

<script>
export default {
  name: "RecipeDetailHeader",
  props: {
    // 레시피 제목, 열량, 대표이미지, 찜여부 등의 정보를 받아옴
    recipeInfo: Object,
  },
};
</script>

<style>
.recipeName {
  color: #5b574b;
  text-align: center;
}
.recipeKcal {
  color: #5b574b;
  opacity: 30%;
  text-align: center;
}
</style>
