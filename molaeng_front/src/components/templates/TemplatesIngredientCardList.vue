<template>
  <div>
    <ingredient-card
      v-for="(ingredientCategory, index) in allIngredients"
      :key="index"
      :ingredientCategory="ingredientCategory"
      :keyWord="keyWord"
    />
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import IngredientCard from "../organisms/OrganismsIngredientCard.vue";
export default {
  name: "IngredientCardList",
  components: {
    IngredientCard,
  },
  props: { keyWord: String },
  computed: {
    ...mapGetters(["allIngredients"]),
  },
};
</script>

<style></style>
