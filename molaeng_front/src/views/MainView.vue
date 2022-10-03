<template>
  <div class="mainPage">
    <div class="mainPageContent">
      <On-Boarding></On-Boarding>
      <Main-Menu></Main-Menu>
      <Kakao-Login></Kakao-Login>
    </div>
  </div>
</template>

<script>
import OnBoarding from "../components/molecules/MoleculesOnBoarding.vue";
import MainMenu from "../components/templates/TemplatesMainMenu.vue";
import KakaoLogin from "../components/atoms/AtomKakaoLogin.vue";

export default {
  name: "MainView",
  components: {
    OnBoarding,
    MainMenu,
    KakaoLogin,
  },

  data() {
    return {};
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
