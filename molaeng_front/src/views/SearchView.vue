<template>
  <div>
    <menu-tab />
    <div id="content">
      <selected-tag-list />
      <ingredient-card-list />
    </div>
    <under-bar-button :text="buttonText" />
  </div>
</template>

<script>
import MenuTab from "../components/molecules/MoleculesMenuTab.vue";
import SelectedTagList from "../components/molecules/MoleculesSelectedTagList.vue";
import IngredientCardList from "../components/templates/TemplatesIngredientCardList.vue";
import UnderBarButton from "../components/atoms/AtomsUnderBarButton.vue";
export default {
  name: "SearchView",
  components: {
    MenuTab,
    SelectedTagList,
    IngredientCardList,
    UnderBarButton,
  },
  data: () => ({
    buttonText: "레시피 검색",
  }),
};
</script>

<style>
#content {
  padding-bottom: 52px;
  overflow: auto;
}
</style>
