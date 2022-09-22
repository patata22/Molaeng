<template>
  <v-tabs centered fixed-tabs>
    <menu-tab-item
      v-for="(title, index) in titles"
      :key="index"
      :text="title"
      :requestUrl="title"
    />
  </v-tabs>
</template>

<script>
import MenuTabItem from "../atoms/AtomsMenuTabItem.vue";
export default {
  name: "MenuTab",
  components: {
    MenuTabItem,
  },
  data: () => ({
    titles: ["재료", "레시피"],
  }),
};
</script>

<style></style>
