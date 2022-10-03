<template>
  <div>
    <home-price-card :recipeId="recipeId" />
    <out-price-card
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
  },
  components: {
    OutPriceCard,
    HomePriceCard,
  },
};
</script>

<style></style>
