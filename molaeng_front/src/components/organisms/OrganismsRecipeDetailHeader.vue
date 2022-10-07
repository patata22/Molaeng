<template>
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
      <recipe-ingredient-tag-list
        :ingredientList="recipeIngredientList"
        class="box"
      />
    </div>
    <v-snackbar
      color="carrot"
      rounded="pill"
      text
      centered
      v-model="snackbar"
      :timeout="timeout"
    >
      로그인이 필요한 기능입니다!
    </v-snackbar>
  </div>
</template>

<script>
import RecipeIngredientTagList from "../molecules/MoleculesRecipeIngredientTagList.vue";
import API from "@/api/APIs";
const api = API;

export default {
  name: "RecipeDetailHeader",
  components: {
    RecipeIngredientTagList,
  },
  props: {
    userId: Number,
    // 레시피 제목, 열량, 대표이미지, 찜여부
    // 등의 정보를 받아옴
    recipeInfo: Object,
    recipeId: String,
    recipeIngredientList: Array,
  },
  data: () => ({
    snackbar: false,
    timeout: 1500,
  }),
  methods: {
    //요청을 보내고, RecipeView 새로고침X
    registRecipeLike() {
      if (this.userId != 0) {
        let temp = this.recipeInfo;
        api.registRecipeLike(this.recipeId, this.userId);
        temp.isLiked = !temp.isLiked;
      } else {
        this.snackbar = true;
      }
    },
    deleteRecipeLike() {
      let temp = this.recipeInfo;
      api.dislikeRecipe(this.recipeId, this.userId);
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
/* 가로스크롤
display: flex;
flex-wrap: nowrap;
overflow-x: auto; */
.box {
  overflow: auto;
  white-space: nowrap;
  margin: 3% 5% 3% 5%;
  padding-bottom: 3%;
}
::-webkit-scrollbar {
  height: 10px;
}
::-webkit-scrollbar-track {
  background: white;
}
::-webkit-scrollbar-thumb {
  background: #cecdc9;
  border-radius: 10px;
}
</style>
