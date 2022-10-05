import axios from "axios";
import API from "./APIs";
const api = API;

const MolaengURL = "http://localhost:8081";
// const MolaengURL = "https://j7a604.p.ssafy.io";
const KakaoRedirectURL = MolaengURL + "/login";
const ClientId = "73e5a007dbb6c4473ba7ef95128857ff";
const ClientSecret = "Y4AFwDzPKpLFtAdcSnT11W46zgHL2noa";
const KakaoAPI = {
  async KakaoLogin() {
    let code = new URL(window.location.href).searchParams.get("code");
    if (code) {
      axios
        .post(
          "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=" +
            ClientId +
            "&redirect_uri=" +
            KakaoRedirectURL +
            "&code=" +
            code +
            "&client_secret=" +
            ClientSecret,
          {
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
          }
        )
        .then((res) => {
          axios
            .get("https://kapi.kakao.com/v2/user/me", {
              headers: {
                "Content-Type":
                  "application/x-www-form-urlencoded;charset=utf-8",
                Authorization: "Bearer " + res.data.access_token,
              },
            })
            .then((res) => {
              api
                .Login(res.data.id, res.data.kakao_account.profile.nickname)
                .then((res) => {
                  document.cookie = "userId=" + res.userId;
                  document.cookie = "nickname=" + res.nickname;
                  if (res.isMember) window.location.replace(MolaengURL);
                  else window.location.replace(MolaengURL + "/myPage");
                });
            });
        });
    }
  },
  KakaoAuth() {
    window.Kakao.Auth.authorize({
      redirectUri: KakaoRedirectURL,
    });
  },
};

export default KakaoAPI;
