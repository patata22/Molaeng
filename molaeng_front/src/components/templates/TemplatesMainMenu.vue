<template>
  <div id="back">
    <div class="d-flex justify-space-between">
      <div class="column">
        <div class="icon">
          <v-btn text icon color="dark" @click="goIngredientSearch">
            <v-icon x-large>mdi-chef-hat</v-icon></v-btn
          >
          <h4>재료로 검색</h4>
        </div>
      </div>
      <div class="column">
        <div class="icon">
          <v-btn text icon color="dark" @click="goRecipeSearch">
            <v-icon x-large>mdi-food-fork-drink</v-icon>
          </v-btn>
          <h5>레시피로 검색</h5>
        </div>
      </div>
    </div>
    <div class="d-flex flex-row justify-space-between">
      <div class="column">
        <div class="icon">
          <v-btn text icon color="dark" @click="goMyPage">
            <v-icon x-large>mdi-face-man</v-icon></v-btn
          >
          <h4>마이페이지</h4>
        </div>
      </div>
      <div class="column">
        <div class="icon">
          <v-btn text icon color="dark" @click="goDiary"
            ><v-icon x-large>mdi-piggy-bank</v-icon></v-btn
          >
          <h4>모랭 일기</h4>
        </div>
      </div>
    </div>
    <div class="column-bottom">
      <div class="icon">
        <v-btn text icon color="dark" @click="goMyPage"
          ><v-icon x-large>mdi-piggy-bank</v-icon></v-btn
        >
        <h4>모랭 일기</h4>
      </div>
    </div>
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

<style>
#back {
  text-align: center;
  background-color: white;
  width: 90%;
  height: calc(100vh - 445px);
  border-radius: 20px;
  margin: 0 auto;
}
#back.column {
  text-align: center;
  width: 45%;
  height: calc((100vh - 445px) / 3);
  /* background-color: #5b574b; */
  opacity: 15%;
  border-radius: 20px;
  margin: 0 auto;
}
#back .column-bottom {
  text-align: center;
  width: 95%;
  height: calc((100vh - 445px) / 3);
  background-color: #5b574b;
  opacity: 15%;
  border-radius: 20px;
  margin: 0 auto;
}
.icon {
  text-align: center;
  padding: 25%;
  width: 100%;
  height: 100%;
  /* color: red; */
  /* opacity: 100% !important; */
}
</style>
