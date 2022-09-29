<template>
  <!-- <button v-on:click="tempToRecipe">레시피로 찾기</button> -->
  <organisms-search-recipe></organisms-search-recipe>
</template>

<script>
import OrganismsSearchRecipe from "../organisms/OrganismsSearchRecipe.vue";
export default {
  name: "SearchRecipe",
  components: {
    OrganismsSearchRecipe,
  },
  methods: {
    // tempToRecipe: function () {
    //   this.$router.push({ path: "/recipe" });
    // },
  },
};
</script>

<style></style>
