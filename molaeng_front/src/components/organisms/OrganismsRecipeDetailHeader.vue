<template>
  <div>
    <v-img
      :lazy-src="recipeInfo.recipeImg"
      height="auto"
      width="100%"
      :src="recipeInfo.recipeImg"
    ></v-img>
    <div
      v-if="recipeInfo.isLiked"
      class="isLikedIcon"
      @click="deleteRecipeLike"
    >
      <v-icon large color="#ED8A53">mdi-cards-heart</v-icon>
    </div>
    <div v-else class="isLikedIcon" @click="registRecipeLike">
      <v-icon large color="#ED8A53">mdi-cards-heart-outline</v-icon>
    </div>
    <div style="text-align: center">
      <div style="color: #5b574b; font-size: x-large; font-weight: bold">
        {{ recipeInfo.recipeName }}
      </div>
      <div style="color: #5b574b; opacity: 30%">
        열량 {{ recipeInfo.recipeKcal }}kcal
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RecipeDetailHeader",
  props: {
    // 레시피 제목, 열량, 대표이미지, 찜여부 등의 정보를 받아옴
    recipeInfo: Object,
    recipeId: String,
  },
  methods: {
    //요청을 보내고, RecipeView로 리다이렉트..?
    registRecipeLike() {
      this.$store.dispatch("registRecipeLike", this.recipeId);
      this.$router.go("/recipe/" + this.recipeId);
    },
    deleteRecipeLike() {
      this.$store.dispatch("deleteRecipeLike", this.recipeId);
      this.$router.go("/recipe/" + this.recipeId);
    },
  },
};
</script>

<style scoped>
.isLikedIcon {
  display: flex;
  justify-content: right;
  padding-top: 2%;
  padding-right: 2%;
}
</style>
