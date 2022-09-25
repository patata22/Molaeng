<template>
  <div class="d-flex" id="header">
    <v-btn class="ma-2" text icon color="dark">
      <v-icon x-large>mdi-penguin</v-icon>
    </v-btn>
    <div absolute right class="d-flex justify-end ma-2">
      <v-btn text icon color="dark" class="ma-1">
        <v-icon large>mdi-magnify</v-icon>
      </v-btn>
      <v-btn text icon color="dark" class="ma-1">
        <v-icon large>mdi-menu</v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
#header {
  justify-content: space-between;
}
</style>
