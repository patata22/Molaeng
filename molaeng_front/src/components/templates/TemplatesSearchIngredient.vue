<template>
  <div>
    <div id="content">
      <selected-tag-list />
      <ingredient-card-list />
    </div>
    <under-bar-button :text="buttonText" :to="buttonLink" />
  </div>
</template>

<script>
import SelectedTagList from "@/components/molecules/MoleculesSelectedTagList.vue";
import IngredientCardList from "@/components/templates/TemplatesIngredientCardList.vue";
import UnderBarButton from "@/components/atoms/AtomsUnderBarButton.vue";
export default {
  name: "SearchIngredient",
  components: {
    SelectedTagList,
    IngredientCardList,
    UnderBarButton,
  },
  data: () => ({
    buttonText: "레시피 찾기",
    buttonLink: "/recipeList",
  }),
};
</script>

<style></style>
