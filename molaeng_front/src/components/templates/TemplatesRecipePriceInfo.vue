<template>
  <div class="mx-auto" style="max-width: 420px">
    <home-price-card
      class="mt-10"
      :recipeId="recipeId"
      :recipePrice="recipePrice"
      :totalPrice="totalPrice"
      :ingredientList="ingredientList"
    />
    <out-price-card
      class="my-10"
      :recipeId="recipeId"
      :outeat="outeat"
      :recipePrice="recipePrice"
    ></out-price-card>
  </div>
</template>

<script>
import OutPriceCard from "@/components/organisms/OrganismsOutPriceCard.vue";
import HomePriceCard from "@/components/organisms/OrganismsRecipeHomePriceCard.vue";

export default {
  name: "RecipePriceInfo",
  props: {
    recipeId: String,
    outeat: Object,
    recipePrice: Number,
    ingredientList: Array,
    totalPrice: Number,
  },
  components: {
    OutPriceCard,
    HomePriceCard,
  },
};
</script>

<style></style>
