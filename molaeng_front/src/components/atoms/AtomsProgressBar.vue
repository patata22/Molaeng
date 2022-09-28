<template>
  <v-progress-linear
    color="primary"
    background-color="semidark"
    value="30"
    height="8"
    rounded
  >
  </v-progress-linear>
</template>
<script>
export default {
  created() {
    this.Ratio = this.ratio;
  },
  props: {
    ratio: Number,
  },
  data: function () {
    return {
      Ratio: 0,
      Active: false,
    };
  },
};
</script>
<style>
.v-progress-linear {
  padding: 0;
}
</style>
