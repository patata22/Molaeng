<template>
  <v-card>
    <v-card-title background-color="yellow">
      <v-row>
        <v-col>
          <v-icon>mdi-star</v-icon>
        </v-col>
        <v-col>
          <v-icon>mdi-star</v-icon>
        </v-col>
        <v-col>
          <v-icon>mdi-star</v-icon>
        </v-col>
        <v-col>
          <v-icon>mdi-star</v-icon>
        </v-col>
        <v-col>
          <v-icon>mdi-star</v-icon>
        </v-col>
        <v-spacer />
        <v-col> 5,780원 더 필요해요! </v-col>
      </v-row>
      <v-row> {{ Recipe.recipeName }}</v-row>
    </v-card-title>
    <v-card-text>
      <v-row>
        <v-col> 사진 위치 </v-col>
        <v-col>
          <v-col>양송이</v-col>
          <v-col>우유</v-col>
          <v-col>양파</v-col>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
<script>
export default {
  props: ["Recipe"],
};
</script>
<style lang=""></style>
