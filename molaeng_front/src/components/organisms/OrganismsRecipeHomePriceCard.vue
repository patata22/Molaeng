<template>
  <v-card id="card" class="mx-auto rounded-lg">
    <v-card-title
      id="title"
      class="py-3 px-5 dark--text d-flex justify-space-between"
    >
      <span class="font-weight-bold">만들어먹을 때 가격</span>
      <span class="text-subtitle-1 font-weight-medium"
        >{{ totalPrice.toLocaleString() }}원</span
      >
    </v-card-title>

    <home-price-info :ingredientList="ingredientList" />
    <v-divider class="mx-5"></v-divider>
    <v-card-text class="pr-5 text-right carrot--text font-weight-bold"
      >{{ recipePrice.toLocaleString() }}원이 더 필요해요!
    </v-card-text>
  </v-card>
</template>

<script>
import HomePriceInfo from "@/components/molecules/MoleculesHomePriceInfo.vue";

export default {
  name: "IngredientPriceCard",
  components: {
    HomePriceInfo,
  },
  props: {
    recipeId: String,
    totalPrice: Number,
    recipePrice: Number,
    ingredientList: Object,
  },
};
</script>

<style>
.v-card__text {
  letter-spacing: 0.05em;
  font-size: 16px;
}
</style>
