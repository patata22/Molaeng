<template>
  <div>
    <!-- <button v-on:click="tempToRecipe">레시피로 찾기</button> -->
    <organisms-search-recipe></organisms-search-recipe>
    <!-- <under-bar-button :text="buttonText" :to="buttonLink" /> -->
  </div>
</template>

<script>
import OrganismsSearchRecipe from "../organisms/OrganismsSearchRecipe.vue";
// import UnderBarButton from "@/components/atoms/AtomsUnderBarButton.vue";
export default {
  name: "SearchRecipe",
  data: function () {
    return {
      buttonText: "레시피 찾기",
    };
  },
  components: {
    OrganismsSearchRecipe,
    // UnderBarButton,
  },
  methods: {
    // tempToRecipe: function () {
    //   this.$router.push({ path: "/recipe" });
    // },
  },
};
</script>

<style></style>
