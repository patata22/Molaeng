<template>
  <v-card id="card" class="mx-auto rounded-lg">
    <v-card-title
      id="title"
      class="py-3 px-5 dark--text d-flex justify-space-between"
    >
      <span class="font-weight-bold">만들어먹을 때 가격</span>
      <span class="text-subtitle-1 font-weight-medium">{{ totalPrice }}원</span>
    </v-card-title>

    <ingredient-price-list />
    <v-divider class="mx-5"></v-divider>
    <v-card-text class="pr-5 text-right carrot--text font-weight-bold"
      >{{ needPrice }}원이 더 필요해요!
    </v-card-text>
  </v-card>
</template>

<script>
import IngredientPriceList from "../molecules/MoleculesIngredientPriceList.vue";
export default {
  name: "IngredientPriceCard",
  components: {
    IngredientPriceList,
  },
  data: () => ({
    totalPrice: "12700",
    needPrice: "6400",
  }),
};
</script>

<style>
.v-card__text {
  letter-spacing: 0.05em;
  font-size: 16px;
}
</style>
