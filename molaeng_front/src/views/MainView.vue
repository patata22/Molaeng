<template>
  <div class="mainPage">
    <div class="mainPageContent mx-auto" style="max-width: 420px">
      <On-Boarding></On-Boarding>
      <Main-Menu :isLogined="isLogined"></Main-Menu>
      <Kakao-Login
        class="mainLoginBtn"
        v-on:kakaoLogin="kakaoLogin"
        v-if="!isLogined"
      ></Kakao-Login>
    </div>
  </div>
</template>

<script>
import OnBoarding from "../components/molecules/MoleculesOnBoarding.vue";
import MainMenu from "../components/templates/TemplatesMainMenu.vue";
import KakaoLogin from "../components/atoms/AtomKakaoLogin.vue";
import KakaoAPI from "@/api/KakaoAPI";

const kakao = KakaoAPI;

export default {
  name: "MainView",
  components: {
    OnBoarding,
    MainMenu,
    KakaoLogin,
  },
  data() {
    return {
      isLogined: false,
    };
  },
  async created() {
    this.checkLogin();
  },
  methods: {
    checkLogin() {
      if (parseInt(this.$cookies.get("userId")) > 0) {
        this.isLogined = true;
        console.log("있음" + this.isLogined);
      } else {
        this.isLogined = false;
        console.log("없음" + this.isLogined);
      }
    },
    async kakaoLogin() {
      await kakao.KakaoAuth();
    },
  },
};
</script>
<style scoped>
.mainPage {
  display: flex;
  height: 100%;
  align-items: center;
  background-color: #fef3c6;
}
.mainPageContent {
  width: 100%;
}
.mainLoginBtn {
  margin-top: 4%;
}
</style>
