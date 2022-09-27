<template>
  <v-btn
    id="ingredient-tag"
    rounded
    class="font-weight-bold ma-1"
    :color="isSelected ? 'carrot' : 'dark'"
    outlined
    @click="select(ingredient)"
    >{{ ingredient.ingredientName }}</v-btn
  >
</template>
<script>
export default {
  name: "IngredientTag",
  props: {
    ingredient: Object,
  },
  computed: {
    isSelected() {
      return this.ingredient.selected;
    },
  },
  data: () => ({}),
  methods: {
    select(ingredient) {
      if (!ingredient.selected) {
        this.$store.commit("ADD_CART", ingredient);
      } else {
        this.$store.commit("REMOVE_CART", ingredient);
      }
    },
  },
};
</script>

<style>
#ingredient-tag {
  border-width: 2px;
}
</style>
