<template>
  <button v-on:click="tempToRecipe">레시피로 찾기</button>
</template>

<script>
export default {
  name: "SearchRecipe",
  methods: {
    tempToRecipe: function () {
      this.$router.push({ path: "/recipe" });
    },
  },
};
</script>

<style></style>
