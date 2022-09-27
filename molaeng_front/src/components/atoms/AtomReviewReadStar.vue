<template lang="">
  <v-col cols="1" v-if="reviewScore >= score">
    <v-icon color="yellow">mdi-star</v-icon>
  </v-col>
  <v-col cols="1" v-else>
    <v-icon color="yellow">mdi-star-outline</v-icon>
  </v-col>
</template>
<script>
export default {
  props: {
    score: { type: Number },
    reviewScore: { type: Number },
  },
};
</script>
<style></style>
