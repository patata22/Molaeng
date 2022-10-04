<template>
  <v-btn
    id="underButton"
    tile
    fixed
    depressed
    x-large
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
  max-width: 420px;
  width: 100%;
  bottom: 0;
  left: 50%;
  transform: translate(-50%, 0);
}
</style>
