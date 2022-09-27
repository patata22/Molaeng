<template>
  <v-tab-item>
    <router-view></router-view>
  </v-tab-item>
</template>

<script>
export default {
  name: "TabItem",
  props: {
    tabLink: String,
  },
};
</script>

<style></style>
