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
        <p id="name">{{ userInfo.name }}</p>
        <p id="dear">님</p>
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
export default {
  name: "MemberMenu",
  components: {},
  data: () => ({
    userInfo: {
      name: "김펭귄",
    },
  }),
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
      // 현재에는 비로그인 메뉴 테스트용으로, 비로그인 페이지로 이동하게 설정함. 어떻게 할지 물어봐야 함.
      this.$router.push("/menu/user").catch(() => {});
    },
  },
};
</script>
<style>
#profile {
  height: 220px;
  width: 100%;
}
#profile #name {
  font-size: 6vw;
  font-weight: bold;
}
#profile #dear {
  font-size: 4vw;
  font-weight: bold;
  margin-left: 5px;
  margin-top: 2vw;
}
#menuList {
  text-align: center;
}
#menuList p {
  font-size: 5vw;
  margin: 0;
  font-weight: bold;
}
#menuList .divider {
  width: 80%;
  margin: 0 auto;
}
#menuList .icon {
  margin: 2vw;
  font-size: 6vw;
  padding: 0;
}
#menuList .menuItem {
  margin: 2vw;
}
</style>
