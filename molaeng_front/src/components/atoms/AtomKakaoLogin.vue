<template>
  <div align="center">
    <v-btn id="loginBtn" @click="login">
      <img src="../../assets/kakaoIcon.png" alt="" id="kakaoLogo" />
      <h4>카카오 로그인</h4>
    </v-btn>
  </div>
</template>
<script>
export default {
  name: "KakaoLogin",
  methods: {
    async login() {
      this.$emit("kakaoLogin");
    },
  },
};
</script>
<style>
#loginBtn {
  width: 90%;
  height: 50px;
  text-align: center;
  margin-top: 4%;
  margin-bottom: 4%;
  border-radius: 12px;
  background-color: #fee0a0;
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
}
#kakaoLogo {
  height: 28px;
  margin-right: 4%;
}
h3 {
  color: #5b574b;
  font-weight: 800;
}
</style>
