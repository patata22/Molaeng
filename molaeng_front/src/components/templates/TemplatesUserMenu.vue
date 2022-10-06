<template>
  <div>
    <div id="logo" style="text-align: center; margin-top: 40%">
      <img src="@/assets/logo.png" alt="" width="85%" max-width="400px" />
    </div>
    <div class="dark--text my-10" id="comment">
      <h2>Molaeng에 로그인해서</h2>
      <h2>더 많은 기능을 사용해보세요!</h2>
    </div>
    <div>
      <kakao-login id="loginBtn" v-on:kakaoLogin="kakaoLogin"></kakao-login>
    </div>
  </div>
</template>
<script>
import KakaoLogin from "../atoms/AtomKakaoLogin.vue";
import KakaoAPI from "@/api/KakaoAPI";

const kakao = KakaoAPI;

export default {
  name: "UserMenu",
  components: {
    KakaoLogin,
  },
  data: () => ({
    userId: 0,
  }),
  created() {
    this.getUserIdByCookie();
  },
  methods: {
    async kakaoLogin() {
      await kakao.KakaoAuth();
    },
    getUserIdByCookie() {
      let userId = this.$cookies.get("userId");
      if (userId) {
        this.$router.replace("/");
      } else {
        this.userId = parseInt(userId);
      }
    },
  },
};
</script>
<style>
#logo {
  width: 100%;
  height: 30%;
  margin-top: 20%;
}
#comment {
  text-align: center;
}
#loginBtn {
  width: 280px;
  margin: 0 auto;
}
#loginBtn #kakao {
  font-size: 100px;
}
</style>
