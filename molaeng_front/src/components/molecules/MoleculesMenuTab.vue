<template>
  <v-tabs fixed-tabs>
    <menu-tab-item v-for="(tab, index) in tabs" :key="index" :tab="tab" />
  </v-tabs>
</template>

<script>
import MenuTabItem from "../atoms/AtomsMenuTabItem.vue";
export default {
  name: "MenuTab",
  components: {
    MenuTabItem,
  },
  props: {
    tabs: Array,
  },
};
</script>

<style>
.v-slide-group__prev {
  display: none !important;
}
</style>
