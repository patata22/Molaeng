<template>
  <div>
    <organisms-search-recipe></organisms-search-recipe>
  </div>
</template>

<script>
import OrganismsSearchRecipe from "../organisms/OrganismsSearchRecipe.vue";
export default {
  name: "SearchRecipe",
  data: function () {
    return {
      buttonText: "레시피 찾기",
    };
  },
  components: {
    OrganismsSearchRecipe,
  },
  methods: {},
};
</script>

<style></style>
