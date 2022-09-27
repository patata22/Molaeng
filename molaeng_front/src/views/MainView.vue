<template>
  <div class="background">
    <Nav-Bar></Nav-Bar>
    <On-Boarding></On-Boarding>
    <Main-Menu></Main-Menu>
    <Kakao-Login></Kakao-Login>
  </div>
</template>

<script>
import NavBar from "../components/molecules/MoleculesNavBar.vue";
import OnBoarding from "../components/molecules/MoleculesOnBoarding.vue";
import MainMenu from "../components/templates/TemplatesMainMenu.vue";
import KakaoLogin from "../components/atoms/AtomKakaoLogin.vue";

export default {
  name: "MainView",
  components: {
    NavBar,
    OnBoarding,
    MainMenu,
    KakaoLogin,
  },

  data() {
    return {};
  },
};
</script>
<style>
.background {
  background-color: #fef3c6;
  width: 100%;
  height: 100%;
}
</style>
