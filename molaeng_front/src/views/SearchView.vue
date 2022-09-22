<template>
  <div>
    <menu-tab />
    <div id="content">
      <ingredient-card-list />
    </div>
    <under-bar-button :text="buttonText" />
  </div>
</template>

<script>
import MenuTab from "../components/molecules/MoleculesMenuTab.vue";
import IngredientCardList from "../components/templates/TemplatesIngredientCardList.vue";
import UnderBarButton from "../components/atoms/AtomsUnderBarButton.vue";
export default {
  name: "SearchView",
  components: {
    IngredientCardList,
    MenuTab,
    UnderBarButton,
  },
  data: () => ({
    buttonText: "레시피 저장",
  }),
};
</script>

<style>
#content {
  padding-bottom: 52px;
  overflow: auto;
}
</style>
