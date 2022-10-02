<template>
  <div>
    <out-price-card
      :recipeId="recipeId"
      :outeat="outeat"
      :recipePrice="recipePrice"
    ></out-price-card>
  </div>
</template>

<script>
import OutPriceCard from "@/components/organisms/OrganismsOutPriceCard.vue";

export default {
  name: "RecipePriceInfo",
  props: {
    recipeId: String,
    outeat: Object,
    recipePrice: Number,
  },
  components: {
    OutPriceCard,
  },
};
</script>

<style></style>
