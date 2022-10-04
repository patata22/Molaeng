<template>
  <div align="center">
    <v-btn
      id="loginBtn"
      href="https://kauth.kakao.com/oauth/authorize?client_id=73e5a007dbb6c4473ba7ef95128857ff&redirect_uri=http://localhost:8080/molaeng/oauth/kakaoLogin&response_type=code"
    >
      <img src="../../assets/kakaoIcon.png" alt="" id="kakaoLogo" />
      <h4>카카오 로그인</h4>
    </v-btn>
  </div>
</template>
<script>
// import axios from "axios";
import API from "@/api/KakaoAPI";
const api = API;

export default {
  name: "KakaoLogin",
  data() {
    return {
      code: "",
      result: {},
      user: {},
      url: "",
    };
  },
  methods: {
    async login() {
      let result = await api.login();
      console.log("함수 끝!!!!" + result);
    },
  },
  components: {},
  created() {
    console.log("created 실행!!");
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
