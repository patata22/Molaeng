<template>
  <div align="center">
    <v-container
      class="rounded-xl white"
      style="max-width: 90%; max-height: 45%"
    >
      <v-row class="justify-space-between">
        <v-col
          class="semidark radius ml-4 mt-4"
          align="center"
          @click="goIngredientSearch"
        >
          <div class="icon">
            <v-btn text icon color="dark">
              <v-icon x-large>mdi-chef-hat</v-icon></v-btn
            >
            <h4>재료로 검색</h4>
          </div>
        </v-col>
        <v-col
          class="semidark radius mr-4 mt-4 ml-2"
          align="center"
          @click="goRecipeSearch"
        >
          <div class="icon">
            <v-btn text icon color="dark">
              <v-icon x-large>mdi-food-fork-drink</v-icon>
            </v-btn>
            <h5>레시피로 검색</h5>
          </div>
        </v-col>
      </v-row>
      <v-row class="justify-space-between">
        <v-col
          class="semidark radius mt-2 ml-4"
          align="center"
          @click="goMyPage"
        >
          <div class="icon">
            <v-btn text icon color="dark">
              <v-icon x-large>mdi-face-man</v-icon></v-btn
            >
            <h4>마이페이지</h4>
          </div>
        </v-col>
        <v-col
          class="semidark radius mr-4 mt-2 ml-2"
          align="center"
          @click="goDiary"
        >
          <div class="icon">
            <v-btn text icon color="dark"
              ><v-icon x-large>mdi-piggy-bank</v-icon></v-btn
            >
            <h4>모랭 일기</h4>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col class="semidark radius ma-4" align="center">
          <div class="icon">
            <v-btn text icon color="dark" @click="goMyPage"
              ><v-icon x-large>mdi-piggy-bank</v-icon></v-btn
            >
            <h4>모랭 일기</h4>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "MainMenu",
  methods: {
    goMyPage() {
      this.$router.push("/myPage").catch(() => {});
    },
    goDiary() {
      this.$router.push("/diary").catch(() => {});
    },
    goIngredientSearch() {
      this.$router.push("/search").catch(() => {});
    },
    goRecipeSearch() {
      this.$router.push("/search").catch(() => {});
    },
  },
};
</script>

<style scoped>
.icon {
  width: 100%;
  height: 100%;
}
.radius {
  border-radius: 12px;
}
</style>
