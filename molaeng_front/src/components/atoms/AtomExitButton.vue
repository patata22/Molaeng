<template>
  <div class="d-flex justify-end">
    <v-btn class="ma-2" text icon color="dark" @click="exit">
      <v-icon x-large>mdi-close</v-icon>
    </v-btn>
  </div>
</template>
<script>
export default {
  name: "ExitButton",
  methods: {
    exit() {
      this.$router.back();
    },
  },
};
</script>
<style></style>
