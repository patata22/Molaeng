<template>
  <div class="d-flex">
    <v-btn class="ma-2" text icon color="dark">
      <v-icon x-large>mdi-penguin</v-icon>
    </v-btn>
    <div absolute right class="d-flex justify-end">
      <v-btn class="" text icon color="dark">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn class="" text icon color="dark">
        <v-icon>mdi-menu</v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
// import {mdiMagnify} from '@mdi/js';
export default {};
</script>

<style>
.group {
  display: flex;
  flex: 1;
  justify-content: space-around;
}
</style>
