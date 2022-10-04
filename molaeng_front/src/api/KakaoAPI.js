// import axios from "axios";

// const kakaoUrl = "";

const API = {
  async login() {
    window.location.replace(
      "https://kauth.kakao.com/oauth/authorize?client_id=73e5a007dbb6c4473ba7ef95128857ff&redirect_uri=http://localhost:8081/molaeng&response_type=code"
    );
  },
};

export default API;
