<template>
  <div id="selectedTagList" class="mx-auto my-5">
    <selected-tag
      v-for="(ingredient, index) in selectedIngredients"
      :key="index"
      :ingredient="ingredient"
    ></selected-tag>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SelectedTag from "../atoms/AtomsSelectedTag.vue";

export default {
  name: "SelectedTagList",
  components: {
    SelectedTag,
  },
  computed: {
    ...mapGetters(["selectedIngredients"]),
  },
};
</script>

<style>
#selectedTagList {
  max-width: 351px;
}
</style>
