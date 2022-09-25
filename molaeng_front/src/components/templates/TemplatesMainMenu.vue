<template>
  <v-container class="white" id="menu">
    <v-row>
      <v-col class="column">
        <div class="inside">
          <v-btn class="" text icon color="dark">
            <v-icon x-large>mdi-cart</v-icon>
            <h4>재료로 검색</h4>
          </v-btn>
        </div>
      </v-col>
      <v-col class="column"
        ><v-btn class="" text icon color="dark">
          <v-icon x-large>mdi-chef-hat</v-icon>
        </v-btn></v-col
      >
    </v-row>
    <v-row>
      <v-col class="column">
        <v-btn class="" text icon color="dark">
          <v-icon x-large>mdi-face-man</v-icon>
        </v-btn></v-col
      >
      <v-col class="column"
        ><v-btn class="" text icon color="dark">
          <v-icon x-large>mdi-piggy-bank</v-icon>
        </v-btn></v-col
      >
    </v-row>
    <v-row class="column"
      ><v-btn class="" text icon color="dark">
        <v-icon x-large>mdi-penguin</v-icon>
      </v-btn></v-row
    >
  </v-container>
</template>
<script>
export default {
  setup() {},
};
</script>
<style>
#menu {
  text-align: center;
  background-color: white;
  height: 450px;
  width: 90%;
  margin-bottom: 20px;
}
.column {
  /* background-color: black; */
  height: 150px;
  text-align: center;
}
.inside {
  /* background-color: #5b574b; */
  opacity: 30%;
  height: 100%;
  border-radius: 20px;
}
</style>
