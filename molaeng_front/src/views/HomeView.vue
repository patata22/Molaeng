<template>
  <div>
    <ingredient-tag />
    <selected-tag />
    <div id="tabs">
      <v-tabs>
        <menu-tab
          v-for="(tabName, index) in title"
          :key="index"
          :tabName="tabName"
          :requestUrl="tabName"
        />
      </v-tabs>
    </div>
    <ingredient-card-list />
    <under-bar-button :text="buttonText" />
  </div>
</template>

<script>
import IngredientTag from "../components/atoms/AtomsIngredientTag.vue";
import SelectedTag from "../components/atoms/AtomsSelectedTag.vue";
import MenuTab from "../components/molecules/MoleculesMenuTab.vue";
import UnderBarButton from "../components/atoms/AtomsUnderBarButton.vue";
import IngredientCardList from "../components/templates/TemplatesIngredientCardList.vue";

export default {
  name: "HomeView",
  data: () => ({
    title: ["A", "B", "C"],
    cardTitle: "가나다라",
    buttonText: "레시피 찾기",
  }),
  components: {
    IngredientTag,
    SelectedTag,
    MenuTab,
    UnderBarButton,
    IngredientCardList,
  },
};
</script>

<style scoped></style>
