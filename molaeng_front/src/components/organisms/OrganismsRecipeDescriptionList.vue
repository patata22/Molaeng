<template>
  <v-stepper vertical>
    <!-- <div class="v-stepper v-stepper--vertical v-sheet theme--light"> -->
    <recipe-description-list-item
      v-for="(detail, index) in recipeDetailList"
      :key="index"
      :order="detail.recipeOrder"
      :detailImg="detail.recipeDetailImg"
      :content="detail.recipeContent"
    >
    </recipe-description-list-item>
    <!-- </div> -->
  </v-stepper>
</template>

<script>
import RecipeDescriptionListItem from "../molecules/MoleculesRecipeDescriptionListItem.vue";
export default {
  name: "RecipeDescriptionList",
  components: {
    RecipeDescriptionListItem,
  },
  data: () => ({
    //더미로 여기 넣었는데 원래는 더 상위 컴포넌트인 RecipeView에서 가지고 있고 여기서도 props로 받아와야 함..?
    recipeDetailList: [
      {
        recipeOrder: 1,
        recipeDetailImg:
          "http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00028_1.png",
        recipeContent: "손질된 새우를 끓는 물에 데쳐 건진다.",
      },
      {
        recipeOrder: 2,
        recipeDetailImg:
          "http://www.foodsafetykorea.go.kr/uploadimg/cook/20_00028_2.png",
        recipeContent:
          "연두부, 달걀, 생크림, 설탕에 녹인 무염버터를 믹서에 넣고 간 뒤 새우(1)를 함께 섞어 그릇에 담는다.",
      },
    ],
  }),
};
</script>

<style></style>
