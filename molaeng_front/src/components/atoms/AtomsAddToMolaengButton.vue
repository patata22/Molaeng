<template>
  <v-card style="margin: auto">
    <v-card-title>모랭일기에 등록하기</v-card-title>
    <v-card-text>이 레시피를 모랭일기에 등록하시겠어요?</v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="primary" text @click="addToMolaeng">예</v-btn>
      <v-btn color="primary" text @click="cancel">아니요</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    dialog: Boolean,
  },
  methods: {
    cancel() {
      this.$emit("cancel", false);
    },
    addToMolaeng() {},
  },
};
</script>

<style></style>
