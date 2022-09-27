<template>
  <v-chip
    id="selectedTag"
    v-if="ingredient.selected"
    color="carrot"
    class="my-1 mr-3 font-weight-bold white--text"
    close
    @click:close="deleteIngredient(ingredient)"
  >
    {{ ingredient.ingredientName }}
  </v-chip>
</template>

<script>
export default {
  name: "SelectedTag",
  props: {
    ingredient: Object,
  },
  methods: {
    deleteIngredient(ingredient) {
      this.$store.commit("REMOVE_CART", ingredient);
    },
  },
};
</script>
<style>
#selectedTag {
  box-shadow: 0px 4px 10px #cecdc9;
}
</style>
