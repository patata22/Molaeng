<template>
  <v-tab class="font-weight-bold" elevation-5 :to="tab.tabLink"
    >{{ tab.tabName }}
  </v-tab>
</template>

<script>
export default {
  name: "MenuTabItem",
  props: {
    tab: Object,
  },
};
</script>
<style></style>
