<template>
  <div>
    <div id="logo"><v-icon class="icon">mdi-penguin</v-icon></div>
    <div class="dark--text my-10" id="comment">
      <h2>Molaeng에 로그인해서</h2>
      <h2>더 많은 기능을 사용해보세요!</h2>
    </div>
    <div>
      <kakao-login id="loginBtn"></kakao-login>
    </div>
  </div>
</template>
<script>
import KakaoLogin from "../atoms/AtomKakaoLogin.vue";

export default {
  name: "UserMenu",
  components: {
    KakaoLogin,
  },
  data: () => ({}),
  methods: {
    exit() {
      this.$router.push("/").catch(() => {}); // 임시로 mainPage로 이동하게 해둠. 어떻게 할지 물어봐야 함.
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
