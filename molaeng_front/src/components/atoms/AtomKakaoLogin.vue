<template>
  <div align="center">
    <v-btn id="loginBtn">
      <img
        src="../../assets/kakaoIcon.png"
        alt=""
        id="kakaoLogo"
        @click="getTemp()"
      />
      <h4>카카오 로그인</h4>
    </v-btn>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "KakaoLogin",
  data() {
    return {
      user: {},
      url: "",
      tempList: {},
    };
  },
  methods: {
    // 마무리할 때에 무조건 .env에 키값 따로 빼줘야 합니다 꼭!!
    doKakaoLogin() {
      const url =
        "https://kauth.kakao.com/oauth/authorize?client_id=" +
        // process.env.VUE_APP_KAKAO_JS_KEY +
        "73e5a007dbb6c4473ba7ef95128857ff" +
        "&redirect_uri=" +
        // process.env.VUE_APP_KAKAO_REDIRECT_URL +
        "http://localhost:8080/molaeng/oauth/kakaoLogin" +
        "&response_type=code&";

      this.url = url;
      console.log(this.url);
    },
    getTemp() {
      console.log("로그인 버튼 작동");
      axios
        .get(this.url)
        .then((res) => {
          console.log(res.data);
        })
        .catch((error) => console.log(error));
    },
  },
  components: {},
  created() {
    this.doKakaoLogin();
    console.log("created 실행!!");
    console.log(this.url);
  },
};
</script>
<style>
#loginBtn {
  width: 90%;
  height: 50px;
  text-align: center;
  margin-top: 15px;
  margin-bottom: 20px;
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
