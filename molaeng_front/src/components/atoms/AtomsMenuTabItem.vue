<template>
  <v-tab class="font-weight-bold" elevation-5 @click="goto(requestUrl)"
    >{{ text }}
  </v-tab>
</template>

<script>
export default {
  name: "MenuTabItem",
  props: {
    text: String,
    requestUrl: String,
  },
  methods: {
    goto(requestUrl) {
      console.log(requestUrl);
    },
  },
};
</script>

<style></style>
