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
import axios from "axios";
import API from "@/api/APIs";
const api = API;
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
  created() {
    this.checkedLogin();
    console.log("login : " + this.isLogined);
    let code = new URL(window.location.href).searchParams.get("code");
    if (code) {
      axios
        .post(
          "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=73e5a007dbb6c4473ba7ef95128857ff&redirect_uri=http://localhost:8081&code=" +
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
              api
                .Login(res.data.id, res.data.kakao_account.profile.nickname)
                .then((res) => {
                  console.log(res);
                  this.isLogined = true;

                  this.$cookies.set("userId", res.userId);
                });
            });
        });
    }
  },
  methods: {
    checkedLogin() {
      if (this.$cookies.get("userId") > 0) this.isLogined = true;
      else this.isLogined = false;
    },
    kakaoLogin() {
      window.Kakao.Auth.authorize({
        redirectUri: "http://localhost:8081",
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
