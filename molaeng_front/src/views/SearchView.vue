<template>
  <div>
    <menu-tab :tabs="tabs" />
    <router-view></router-view>
  </div>
</template>

<script>
import MenuTab from "../components/molecules/MoleculesMenuTab.vue";

export default {
  name: "SearchView",
  components: {
    MenuTab,
  },
  data: () => ({
    buttonText: "레시피 검색",
    tabs: [
      {
        tabName: "재료",
        tabLink: "/search/ingredient",
      },
      {
        tabName: "레시피",
        tabLink: "/search/recipe",
      },
    ],
  }),
};
</script>

<style>
#content {
  padding-bottom: 52px;
  overflow: auto;
}
</style>
