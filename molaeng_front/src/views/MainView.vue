<template>
  <div class="mainPage">
    <div class="mainPageContent">
      <On-Boarding></On-Boarding>
      <Main-Menu></Main-Menu>
      <Kakao-Login v-on:kakaoLogin="kakaoLogin"></Kakao-Login>
    </div>
  </div>
</template>

<script>
import OnBoarding from "../components/molecules/MoleculesOnBoarding.vue";
import MainMenu from "../components/templates/TemplatesMainMenu.vue";
import KakaoLogin from "../components/atoms/AtomKakaoLogin.vue";
import axios from "axios";
export default {
  name: "MainView",
  components: {
    OnBoarding,
    MainMenu,
    KakaoLogin,
  },
  // computed:{
  //   isLogin(){
  //     if(getCookie()>0)
  //     return true;
  //   }
  // },
  created() {
    let code = new URL(window.location.href).searchParams.get("code");
    if (code) {
      axios
        .post(
          "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=73e5a007dbb6c4473ba7ef95128857ff&redirect_uri=http://localhost:8082&code=" +
            code +
            "&client_secret=Y4AFwDzPKpLFtAdcSnT11W46zgHL2noa",
          {
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
          }
        )
        .then((res) => {
          console.log(res);
          axios
            .get("https://kapi.kakao.com/v2/user/me", {
              headers: {
                "Content-Type":
                  "application/x-www-form-urlencoded;charset=utf-8",
                Authorization: "Bearer " + res.data.access_token,
              },
            })
            .then((res) => {
              this.$router.replace("");
              console.log(res);
              axios.post("/user", {
                uuid: res.data.id,
                nickname: res.data.kakao_account.profile.nickname,
              });
              // .then((res) => {
              //   this.setCookie({userid:res.data.result.userid});
              // });
            });
        });
    }
  },
  methods: {
    kakaoLogin() {
      window.Kakao.Auth.authorize({
        redirectUri: "http://localhost:8082",
      });
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
