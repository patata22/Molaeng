<template>
  <v-btn
    id="underButton"
    tile
    fixed
    depressed
    x-large
    block
    color="carrot"
    class="font-weight-bold white--text elevation-0 py-2"
    :to="to"
  >
    {{ text }}
  </v-btn>
</template>

<script>
export default {
  name: "UnderBarButton",
  props: {
    text: String,
    to: String,
  },
};
</script>

<style>
#underButton {
  bottom: 0;
}
</style>
