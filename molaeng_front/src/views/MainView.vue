<template>
  <div class="mainPage">
    <div class="mainPageContent mx-auto" style="max-width: 420px">
      <On-Boarding></On-Boarding>
      <Main-Menu></Main-Menu>
      <Kakao-Login v-on:kakaoLogin="kakaoLogin" v-if="!isLogined"></Kakao-Login>
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
  computed: {
    isLogin() {
      if (this.$cookies.get("userId") > 0) return true;
      else return false;
    },
  },
  async created() {
    this.checkedLogin();
  },
  methods: {
    checkedLogin() {
      if (this.$cookies.get("userId") > 0) this.isLogined = true;
      else this.isLogined = false;
    },
    async kakaoLogin() {
      await kakao.KakaoAuth();
    },
    getCookie(name) {
      var parts = document.cookie.split(name + "=");
      if (parts.length === 2) {
        return parts[1].split(";")[0];
      }
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
</style>
