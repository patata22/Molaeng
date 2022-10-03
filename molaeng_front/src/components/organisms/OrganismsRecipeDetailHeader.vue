<template>
  <div>
    <div>
      <div v-if="recipeInfo.isLiked" class="isLikedIcon">
        <v-icon large color="#ED8A53" @click="deleteRecipeLike"
          >mdi-cards-heart</v-icon
        >
      </div>
      <div v-else class="isLikedIcon">
        <v-icon large color="#ED8A53" @click="registRecipeLike"
          >mdi-cards-heart-outline</v-icon
        >
      </div>
      <div style="text-align: center">
        <div style="color: #5b574b; font-size: x-large; font-weight: bold">
          {{ recipeInfo.recipeName }}
        </div>
        <div style="color: #5b574b; opacity: 30%">
          열량 {{ recipeInfo.recipeKcal }}kcal
        </div>
        <ingredient-tag-list
          :ingredientList="recipeIngredientList"
          style="display: flex; flex-wrap: nowrap; overflow-x: auto"
        />
      </div>
    </div>
  </div>
</template>

<script>
import IngredientTagList from "../molecules/MoleculesIngredientTagList.vue";
import API from "@/api/APIs";
const api = API;

export default {
  name: "RecipeDetailHeader",
  components: {
    IngredientTagList,
  },
  props: {
    // 레시피 제목, 열량, 대표이미지, 찜여부 등의 정보를 받아옴
    recipeInfo: Object,
    recipeId: String,
    recipeIngredientList: Array,
  },
  methods: {
    //요청을 보내고, RecipeView 새로고침X
    registRecipeLike() {
      let temp = this.recipeInfo;
      api.registRecipeLike(this.recipeId);
      temp.isLiked = !temp.isLiked;
    },
    deleteRecipeLike() {
      let temp = this.recipeInfo;
      api.deleteRecipeLike(this.recipeId);
      temp.isLiked = !temp.isLiked;
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
