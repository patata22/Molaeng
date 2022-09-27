<template>
  <div>
    <ingredient-tag
      v-for="(ingredient, index) in ingredientList"
      :key="index"
      :ingredient="ingredient"
    >
    </ingredient-tag>
  </div>
</template>

<script>
import IngredientTag from "../atoms/AtomsIngredientTag.vue";
export default {
  name: "IngredientTagList",
  components: {
    IngredientTag,
  },
  props: {
    ingredientList: Array,
  },
};
</script>

<style></style>
