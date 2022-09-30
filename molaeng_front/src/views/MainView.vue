<template>
  <div class="background">
    <On-Boarding></On-Boarding>
    <Main-Menu></Main-Menu>
    <Kakao-Login></Kakao-Login>
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
.background {
  background-color: #fef3c6;
  width: 100%;
  height: 100%;
}
</style>
