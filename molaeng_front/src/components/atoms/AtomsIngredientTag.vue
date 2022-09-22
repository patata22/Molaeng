<template>
  <v-btn
    id="ingredient-tag"
    rounded
    class="font-weight-bold ma-1"
    :color="tagColor"
    outlined
    @click="isSelected(id) ? 'carrot' : 'dark'"
    >{{ text }}</v-btn
  >
</template>

<script>
export default {
  name: "IngredientTag",
  mounted() {
    this.selected = this.$store.getters.getIngredientById(
      this.$props.id
    ).selected;
  },
  computed: {
    isSelected(id) {
      return this.$store.getters.getIngredientById(id).selected;
    },
  },
  props: {
    text: String,
    id: Number,
  },
  data: () => ({
    selected: false,
    tagColor: "dark",
  }),
  methods: {
    select() {
      this.selected = !this.selected;
    },
  },
};
</script>

<style>
#ingredient-tag {
  border-width: 2px;
}
</style>
