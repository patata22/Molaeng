<template>
  <div id="loginBtn">
    <v-btn color="secondary black--text" width="90%" height="40px">
      <img src="../../assets/kakaoIcon.png" alt="" id="kakao" />
      카카오 로그인
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
}
#kakao {
  width: 28px;
  margin-right: 10px;
}
</style>
