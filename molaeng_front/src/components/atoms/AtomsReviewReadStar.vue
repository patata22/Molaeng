<template lang="">
  <v-rating
    length="5"
    readonly
    medium
    color="yellow"
    background-color="yellow"
    :value="reviewScore"
  ></v-rating>
</template>
<script>
export default {
  props: {
    reviewScore: Number,
  },
};
</script>
<style>
.v-rating .v-icon {
  padding: 0;
}
</style>
