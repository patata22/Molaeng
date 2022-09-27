<template>
  <div>
    <v-row v-for="(recipeName, i) in recipeList" :key="i">
      <v-col>
        <v-icon>mdi-magnify</v-icon>
      </v-col>
      <v-col>
        {{ recipeName }}
      </v-col>
      <v-spacer></v-spacer>
      <v-col>
        <v-icon>mdi-arrow-top-left-thin</v-icon>
      </v-col>
    </v-row>
  </div>
</template>
<script>
export default {
  data: function () {
    return {
      recipeList: ["당근스프", "양송이스프", "옥수수스프"],
    };
  },
};
</script>
<style></style>
