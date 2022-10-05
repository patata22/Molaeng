<template>
  <div>
    <div id="profile">
      <v-img
        src="../../assets/defaultProfileImage.png"
        alt=""
        contain
        height="100px"
        width="100px"
        class="mx-auto my-8 mt-10"
      ></v-img>
      <div class="d-flex justify-center">
        <p id="name">{{ userInfo.nickname }} 님</p>
      </div>
    </div>
    <div id="menuList">
      <div class="menuItem">
        <v-btn class="ma-2" text icon color="dark" @click="goMyPage">
          <v-icon class="icon">mdi-face-man</v-icon>
          <p>마이페이지</p>
        </v-btn>
      </div>
      <v-divider class="divider"></v-divider>
      <div class="menuItem">
        <v-btn class="ma-2" text icon color="dark" @click="goDiary">
          <v-icon class="icon">mdi-piggy-bank</v-icon>
          <p>모랭 일기</p>
        </v-btn>
      </div>
      <v-divider class="divider"></v-divider>
      <div class="menuItem">
        <v-btn class="ma-2" text icon color="dark" @click="goInterestRecipe">
          <v-icon class="icon">mdi-heart</v-icon>
          <p>관심 레시피</p>
        </v-btn>
      </div>
      <v-divider class="divider"></v-divider>
      <div class="menuItem">
        <v-btn class="ma-2" text icon color="dark" @click="logout">
          <v-icon class="icon">mdi-logout-variant</v-icon>
          <p>로그아웃</p>
        </v-btn>
      </div>
      <v-divider class="divider"></v-divider>
    </div>
  </div>
</template>
<script>
import axios from "axios";
// import axios from "axios";
export default {
  name: "MemberMenu",
  components: {},
  data: () => ({
    userInfo: {
      userId: 0,
      nickname: "김펭귄",
    },
  }),
  created() {
    this.getUserIdByCookie();
    this.getUserInfo();
    console.log(this.userInfo);
  },
  methods: {
    exit() {
      this.$router.push("/").catch(() => {});
    },
    goMyPage() {
      this.$router.push("/myPage").catch(() => {});
    },
    goDiary() {
      this.$router.push("/diary").catch(() => {});
    },
    goInterestRecipe() {
      this.$router.push("/interestRecipe").catch(() => {});
    },
    logout() {
      // window.Kakao.Auth.logout().then(
      //   (res) => console.log(res),
      //   console.log(window.Kakao.Auth.getAccessToken())
      // );
      this.deleteCookie();
      this.$router.push("/").catch(() => {});
      console.log("로그아웃!");
    },
    deleteCookie() {
      this.$cookies.remove("userId");
      alert("로그아웃 되었습니다.");
    },
    getUserIdByCookie() {
      this.userInfo.userId = this.$cookies.get("userId");
    },
    getUserInfo() {
      axios
        .post(
          "http://localhost:8080/molaeng/user?userId=" + this.userInfo.userId
        )
        .then((res) => {
          console.log(res);
          this.userInfo.nickname = res.data.result.nickname;
        });
    },
  },
};
</script>

<style>
#profile {
  height: 220px;
}
#profile #name {
  color: #5b574b;
  font-size: 1.8rem;
  font-weight: 800;
}
#menuList {
  text-align: center;
}
#menuList p {
  font-size: 1.3rem;
  margin: 0;
  font-weight: bold;
}
#menuList .divider {
  width: 80%;
  margin: 0 auto;
}
#menuList .icon {
  margin: 6%;
  font-size: 1.5rem;
}
#menuList .menuItem {
  margin: 2%;
}
</style>
