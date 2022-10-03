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
    <div class="needPrice">
      {{ recipePrice.toLocaleString() }}원이 더 필요해요!
    </div>
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
    ingredientList: Array,
  },
};
</script>

<style>
.needPrice {
  padding: 4% 6% 4% 6%;
  text-align: right;
  color: #ed8a53;
  font-weight: bold;
}
</style>
