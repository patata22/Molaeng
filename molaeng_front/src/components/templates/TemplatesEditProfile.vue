<template>
  <div>
    <v-container>
      <v-row class="ma-10">
        <v-img
          src="../../assets/defaultProfileImage.png"
          alt=""
          contain
          height="100px"
          width="100px"
        ></v-img>
      </v-row>
      <v-row class="px-10">
        <v-col>
          <div class="mb-1">
            <h4 class="carrot--text">닉네임</h4>
          </div>
          <v-text-field
            outlined
            color="carrot"
            rounded
            dense
            v-model="userInfo.nickname"
            :disabled="disabled"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row class="px-10">
        <v-col>
          <div class="d-flex mb-1">
            <h4 class="carrot--text">우리 동네</h4>
            <h6 class="carrot--dark text--disabled ml-1 mt-2">
              아직은 서울만 서비스 이용이 가능해요.
            </h6>
          </div>
          <v-select
            placeholder="시/도"
            :items="sidoList"
            outlined
            v-model="sido"
            color="carrot"
            rounded
            dense
            :disabled="disabled"
          ></v-select>
          <v-select
            placeholder="구/군"
            :items="getGugunList"
            item-text="name"
            item-value="value"
            v-model="gugun"
            outlined
            color="carrot"
            rounded
            dense
            v-on:change="selectGugun"
            :disabled="disabled"
          ></v-select>
        </v-col>
      </v-row>
      <v-row class="px-10 justify-center">
        <v-btn
          class="primary font-weight-bold mt-5"
          rounded
          dense
          @click="clickUpdate"
          v-if="disabled === true"
        >
          수정
        </v-btn>
        <v-btn
          class="primary font-weight-bold mt-5"
          rounded
          dense
          @click="updateProfile"
          v-if="disabled === false"
        >
          저장
        </v-btn>
      </v-row>
      <v-snackbar
        color="carrot"
        rounded="pill"
        text
        centered
        v-model="snackbar"
        :timeout="timeout"
      >
        수정되었습니다!
      </v-snackbar>
      <v-row justify="end">
        <v-btn
          color="dark--text justify-end"
          class="large mt-10"
          style="text-weight: bold"
          depressed
          @click.stop="dialog = true"
        >
          <span style="opacity: 40%">탈퇴하기</span>
        </v-btn>
        <v-dialog v-model="dialog" max-width="290">
          <v-card>
            <v-card-title class="text-h5"> 회원 탈퇴 </v-card-title>

            <v-card-text> 정말 탈퇴하실 건가요....? </v-card-text>

            <v-card-actions id="buttonsId">
              <v-spacer></v-spacer>

              <v-btn
                color="primary primary--outlined"
                outlined
                text
                @click="dialog = false"
              >
                <span style="font-weight: bold">취소</span>
              </v-btn>
              <v-btn
                class="primary"
                text
                @click="(dialog = false), deleteUserAccount()"
              >
                <span style="font-weight: bold" class="white--text">탈퇴</span>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </v-container>
  </div>
</template>

<script>
// import axios from "axios";
import { mapGetters } from "vuex";
// import { ContextExclusionPlugin } from "webpack";

import API from "@/api/APIs";
const api = API;

export default {
  name: "EditProfile",

  data() {
    return {
      userInfo: {
        userId: 1,
        // gugunId: 1,
        nickname: "test",
      },
      sido: "서울특별시",
      gugun: 0,
      sidoList: ["서울특별시"],
      disabled: true,
      dialog: false,
      snackbar: false,
      timeout: 1500,
      // gugunList: [{ name: "기본", value: 0 }],
    };
  },
  computed: {
    ...mapGetters(["getGugunList"]),
  },

  // computed: { ...mapGetters(["userId", "nickname", "myRegion", "gugun"]) },
  methods: {
    // ...mapMutations(["SET_GUGUN"]),
    // ...mapActions(["getUserInfo"]),
    clickUpdate() {
      console.log("수정 버튼 클릭");
      this.disabled = false;
      this.snackbar = false;
    },
    async getUser() {
      this.userInfo.userId = this.$cookies.get("userId");
      await api.getUserInfo(this.userInfo.userId).then((res) => {
        this.userInfo.userId = res.result.userId;
        this.userInfo.nickname = res.result.nickname;
        this.gugun = res.result.gugunId;
        console.log(res);
      });
      // console.log(this.userInfo);
    },
    async updateProfile() {
      console.log("저장 버튼 클릭");
      console.log("userInfo req = " + this.userInfo);
      await api
        .updateProfile(this.userInfo.userId, this.userInfo.nickname, this.gugun)
        .then((res) => {
          console.log(res);
          this.disabled = true;
          this.snackbar = true;
        });
    },
    selectGugun() {
      console.log(this.gugun);
    },
    selectSido() {
      console.log(this.sido);
    },
    async deleteUserAccount() {
      console.log("탈퇴 버튼 클릭!!!");
      await api.deleteUser(this.userInfo.userId).then((res) => {
        console.log("탈퇴 res = " + res);
        this.logout();
        this.$router.replace("/").catch(() => {});
      });
    },
    logout() {
      window.Kakao.API.request({
        url: "/v1/user/unlink",
      });
      this.deleteCookie();
      this.$router.replace("/").catch(() => {});
      console.log("로그아웃!");
    },
    deleteCookie() {
      this.$cookies.remove("userId");
      this.$cookies.remove("nickname");
    },
    getUserIdByCookie() {
      let userId = this.$cookies.get("userId");
      if (userId) {
        this.userInfo.userId = parseInt(userId);
      } else {
        this.$router.replace("/");
      }
    },
  },
  async mounted() {
    await this.getUser();
  },
  created() {
    this.getUserIdByCookie();
  },
};
</script>

<style>
#buttonsId .v-btn--outlined {
  border: thin solid #fdac6d;
}
</style>
