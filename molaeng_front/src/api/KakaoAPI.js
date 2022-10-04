const API = {
  async login() {
    window.location.href =
      "https://kauth.kakao.com/oauth/authorize?client_id=73e5a007dbb6c4473ba7ef95128857ff&redirect_uri=http://localhost:8081&response_type=code";
  },
};

export default API;
