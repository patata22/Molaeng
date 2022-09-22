<template>
  <v-chip
    id="selectedTag"
    v-if="isSelected"
    color="carrot"
    class="ma-2 font-weight-bold white--text"
    close
    @click:close="isSelected = false"
  >
    {{ text }}
  </v-chip>
</template>

<script>
export default {
  name: "SelectedTag",
  props: {
    text: String,
  },
  data() {
    return {
      isSelected: true,
    };
  },
};
</script>

<style>
#selectedTag {
  box-shadow: 0px 4px 10px #cecdc9;
}
</style>
