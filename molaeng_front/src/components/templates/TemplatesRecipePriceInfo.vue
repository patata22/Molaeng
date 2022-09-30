<template>
  <div>
    <out-price-card :recipeId="recipeId"></out-price-card>
  </div>
</template>

<script>
import OutPriceCard from "@/components/organisms/OrganismOutPriceCard.vue";

export default {
  name: "RecipePriceInfo",
  props: {
    recipeId: String,
  },
  components: {
    OutPriceCard,
  },
};
</script>

<style></style>
