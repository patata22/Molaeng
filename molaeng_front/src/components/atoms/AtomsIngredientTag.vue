<template>
  <v-btn
    id="ingredient-tag"
    rounded
    class="font-weight-bold ma-1"
    :color="tagColor"
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
    tagColor() {
      if (this.ingredient.selected) {
        return "carrot";
      } else {
        return "dark";
      }
    },
  },
  methods: {
    select(ingredient) {
      if (!ingredient.selected) {
        this.$store.commit("ADD_CART", ingredient);
        this.tagColor = "carrot";
      } else {
        this.$store.commit("REMOVE_CART", ingredient);
        this.tagColor = "dark";
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
