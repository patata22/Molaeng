<template lang="">
  <div>
    <v-col v-if="reviewScore >= score">
      <v-icon color="yellow">mdi-star</v-icon>
    </v-col>
    <v-col v-else>
      <v-icon color="yellow">mdi-star-outline</v-icon>
    </v-col>
  </div>
</template>
<script>
export default {
  props: {
    score: { type: Number },
    reviewScore: { type: Number },
  },
};
</script>
<style lang=""></style>
