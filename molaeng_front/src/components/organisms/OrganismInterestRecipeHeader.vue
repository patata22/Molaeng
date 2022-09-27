<template>
  <div>
    <div class="dark--text font-weight-bold text-center pa-5">
      <h1>관심 레시피</h1>
    </div>
    <v-tabs centered fixed-tabs>
      <menu-tab-item v-for="(tab, index) in tabs" :key="index" :tab="tab" />
    </v-tabs>
  </div>
</template>

<script>
import MenuTabItem from "../atoms/AtomsMenuTabItem.vue";

export default {
  name: "InterestRecipeHeader",
  components: {
    MenuTabItem,
  },
  props: {
    tabs: Array,
  },
};
</script>
