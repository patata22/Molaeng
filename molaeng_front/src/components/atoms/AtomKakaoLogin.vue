<template>
  <div id="loginBtn">
    <v-btn
      color="secondary dark--text font-weight-bold"
      width="90%"
      height="50px"
    >
      <img src="../../assets/kakaoIcon.png" alt="" id="kakao" />
      <h3>카카오 로그인</h3>
    </v-btn>
  </div>
</template>
<script>
export default {
  name: "KakaoLogin",
  methods: {},
  components: {},
};
</script>
<style>
#loginBtn {
  text-align: center;
  margin-top: 15px;
  margin-bottom: 20px;
}
#kakao {
  width: 28px;
  margin-right: 5%;
}
</style>
