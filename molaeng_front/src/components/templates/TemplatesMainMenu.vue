<template>
  <div>
    <v-container class="mainMenuBackground">
      <v-row>
        <v-col class="mainMenu ml-4 mt-4" @click="goIngredientSearch">
          <div class="mainIcon">
            <v-icon color="dark" x-large>mdi-chef-hat</v-icon>
            <h4>재료로 검색</h4>
          </div>
        </v-col>
        <v-col class="mainMenu mr-4 mt-4 ml-2" @click="goRecipeSearch">
          <div class="mainIcon">
            <v-icon color="dark" x-large>mdi-food-fork-drink</v-icon>
            <h4>레시피로 검색</h4>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col class="mainMenu mt-2 ml-4" @click="goMyPage">
          <div class="mainIcon">
            <v-icon color="dark" x-large>mdi-face-man</v-icon>
            <h4>마이페이지</h4>
          </div>
        </v-col>
        <v-col class="mainMenu mr-4 mt-2 ml-2" @click="goDiary">
          <div class="mainIcon">
            <v-icon color="dark" x-large>mdi-piggy-bank</v-icon>
            <h4>모랭 일기</h4>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col class="mainMenu ma-4">
          <img
            src="../../assets/logo.png"
            alt="모두의 냉장고"
            class="mainLogo"
          />
        </v-col>
      </v-row>
    </v-container>
    <v-snackbar
      color="carrot"
      rounded="pill"
      text
      centered
      v-model="snackbar"
      :timeout="timeout"
    >
      로그인이 필요한 기능입니다!
    </v-snackbar>
  </div>
</template>

<script>
export default {
  name: "MainMenu",
  props: {
    isLogined: Boolean,
  },
  data: () => ({
    snackbar: false,
    timeout: 1500,
  }),
  methods: {
    goMyPage() {
      if (this.isLogined) {
        this.$router.push("/myPage").catch(() => {});
      } else {
        this.snackbar = true;
      }
    },
    goDiary() {
      if (this.isLogined) {
        this.$router.push("/diary").catch(() => {});
      } else {
        this.snackbar = true;
      }
    },
    goIngredientSearch() {
      this.$router.push("/search").catch(() => {});
    },
    goRecipeSearch() {
      this.$router.push("/search/recipe").catch(() => {});
    },
  },
};
</script>

<style scoped>
.mainMenuBackground {
  text-align: center;
  max-height: 60%;
  max-width: 90%;
  border-radius: 24px;
  background-color: white;
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
}
.mainMenu {
  display: flex;
  justify-content: center;
  background-color: rgb(91 87 75 / 10%);
  border-radius: 12px;
  box-shadow: inset 0px 5px 10px rgb(91 87 75 / 20%);
}
.mainMenu:active {
  background-color: rgb(91 87 75 / 20%);
}
.mainLogo {
  width: 100%;
}
.mainIcon {
  width: 100%;
  height: 100%;
  margin: 10% 0px;
}
h4 {
  color: #5b574b;
  font-weight: 800;
}
</style>
