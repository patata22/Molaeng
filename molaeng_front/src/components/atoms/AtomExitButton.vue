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
  props: {
    url: String,
  },
  methods: {
    exit() {
      if (this.url) {
        this.$router.replace(this.url);
      } else {
        this.$router.back();
      }
    },
  },
};
</script>
<style></style>
