<template>
  <div>
    <ingredient-tag
      v-for="(ingredient, index) in matchingKeyword"
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
    keyWord: String,
  },
  computed: {
    matchingKeyword() {
      if (this.keyWord == "") {
        return this.ingredientList;
      }
      var temp = [];
      this.ingredientList.forEach((ingredient) => {
        if (ingredient.ingredientName.indexOf(this.keyWord) >= 0) {
          temp.push(ingredient);
        }
      });
      return temp;
    },
  },
};
</script>

<style></style>
