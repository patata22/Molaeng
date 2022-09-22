<template>
  <div>
    <ingredient-card
      v-for="(ingredient, index) in this.ingredients"
      :key="index"
      :ingredient="ingredient"
    />
  </div>
</template>

<script>
import IngredientCard from "../organisms/OrganismsIngredientCard.vue";
export default {
  name: "IngredientCardList",
  components: {
    IngredientCard,
  },
  mounted() {
    this.ingredients = this.$store.getters.getAllIngredients;
  },
  data: () => ({
    ingredients: [],
  }),
};
</script>

<style></style>
