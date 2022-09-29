<template>
  <div>
    <out-price-info :recipeId="recipeId"></out-price-info>
  </div>
</template>

<script>
import OutPriceInfo from "@/components/organisms/OrganismOutPriceInfo.vue";

export default {
  name: "RecipePriceInfo",
  props: {
    recipeId: String,
  },
  components: {
    OutPriceInfo,
  },
};
</script>

<style></style>
