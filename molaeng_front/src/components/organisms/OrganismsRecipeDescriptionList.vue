<template>
  <v-stepper vertical>
    <!-- <div class="v-stepper v-stepper--vertical v-sheet theme--light"> -->
    <recipe-description-list-item
      v-for="(detail, index) in recipeDetailList"
      :key="index"
      :order="detail.recipeOrder"
      :detailImg="detail.recipeDetailImg"
      :content="detail.recipeContent"
    >
    </recipe-description-list-item>
    <!-- </div> -->
  </v-stepper>
</template>

<script>
import RecipeDescriptionListItem from "../molecules/MoleculesRecipeDescriptionListItem.vue";
import { mapGetters } from "vuex";

export default {
  name: "RecipeDescriptionList",
  components: {
    RecipeDescriptionListItem,
  },
  computed: {
    ...mapGetters(["recipeDetailList"]),
  },
};
</script>

<style></style>
