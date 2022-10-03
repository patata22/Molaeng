<template>
  <v-card id="card" class="mx-auto rounded-lg">
    <v-card-title
      id="title"
      class="py-3 px-5 dark--text d-flex justify-space-between"
    >
      <span class="font-weight-bold">만들어먹을 때 가격</span>
      <span class="text-subtitle-1 font-weight-medium">{{ totalPrice }}원</span>
    </v-card-title>

    <home-price-info :ingredientList="ingredientList" />
    <v-divider class="mx-5"></v-divider>
    <v-card-text class="pr-5 text-right carrot--text font-weight-bold"
      >{{ needPrice }}원이 더 필요해요!
    </v-card-text>
  </v-card>
</template>

<script>
import HomePriceInfo from "@/components/molecules/MoleculesHomePriceInfo.vue";
import { mapGetters } from "vuex";
import API from "@/api/APIs";

const api = API;
export default {
  name: "IngredientPriceCard",
  components: {
    HomePriceInfo,
  },
  props: {
    recipeId: String,
  },
  data: () => ({
    totalPrice: 0,
    needPrice: 0,
    ingredientList: [],
  }),
  computed: {
    ...mapGetters(["selectedIngredientIds"]),
  },
  async created() {
    let result = await api.getHomePrice(this.recipeId);
    this.ingredientList = result.ingredientList;

    for (let i = 0; i < this.ingredientList.length; i++) {
      let ingredient = this.ingredientList[i];
      this.totalPrice += Number(ingredient.price);
      if (!this.selectedIngredientIds.includes(ingredient.ingredientId)) {
        this.needPrice += Number(ingredient.price);
      }
    }
  },
};
</script>

<style>
.v-card__text {
  letter-spacing: 0.05em;
  font-size: 16px;
}
</style>
