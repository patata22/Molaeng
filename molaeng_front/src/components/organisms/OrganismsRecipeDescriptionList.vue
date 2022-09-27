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
          "http://file.okdab.com/UserFiles/searching/recipe/002100_p01.jpg",
        recipeContent:
          "쇠고기는 삶아 건져 편육으로 썰고 국물은 식혀 기름을 걷어 육수로 준비한다.",
      },
      {
        recipeOrder: 2,
        recipeDetailImg:
          "http://file.okdab.com/UserFiles/searching/recipe/002100_p02.jpg",
        recipeContent:
          "동치미무는 길쭉하고 얇게 썰고 오이는 어슷썰어 소금에 20분 동안 절였다가 물기를 꼭 짜서 살짝 볶는다.",
      },
    ],
  }),
};
</script>

<style></style>
