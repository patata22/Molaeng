<template>
  <div class="login">
    <div class="innerDiv">
      <img class="loginImg" src="@/assets/login.png" />
      <h1>로그인 중입니다</h1>
    </div>
  </div>
</template>

<script>
import KakaoAPI from "@/api/KakaoAPI";
const kakao = KakaoAPI;
export default {
  async created() {
    this.serverLogin(await kakao.KakaoLogin());
  },
  methods: {
    serverLogin() {
      this.$router.replace("");
    },
  },
};
</script>

<style scoped>
.login {
  background-color: #fef3c6;
  height: 100%;
  width: 100%;
  position: absolute;
}
.loginImg {
  align-self: end;
  height: 25%;
}
.innerDiv {
  height: 100%;
  display: grid;
  color: #5b574b;
  justify-items: center;
}
h1 {
  margin-top: 5%;
  margin-bottom: 25%;
}
</style>
