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
            v-model="userProfile.nickname"
            :error-messages="nicknameDuplicated"
            :disabled="validated ? '' : disabled"
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
            :items="sido"
            outlined
            color="carrot"
            rounded
            dense
            :disabled="validated ? '' : disabled"
            class="mb-3"
          ></v-select>
          <v-select
            placeholder="구/군"
            :items="gugun"
            outlined
            color="carrot"
            rounded
            dense
            :disabled="validated ? '' : disabled"
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

      <v-row justify="end">
        <v-btn
          color="dark--text justify-end "
          class="large mt-10"
          x-large
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

              <v-btn class="primary" text @click="dialog = false">
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
import { mapMutations, mapGetters } from "vuex";
// import API from "@/api/APIs";
// import { ContextExclusionPlugin } from "webpack";

// const api = API;
import axios from "axios";

export default {
  name: "EditProfile",

  data() {
    return {
      userProfile: {
        userId: 1,
        gugunId: 1,
        nickname: "test",
      },
      disabled: true,
      dialog: false,
      gugunList: {},
    };
  },
  computed: {
    ...mapGetters(["getSido", "getGugunList"]),
  },

  // computed: { ...mapGetters(["userId", "nickname", "myRegion", "gugun"]) },
  methods: {
    ...mapMutations(["SET_GUGUN"]),
    // ...mapActions(["getUserInfo"]),
    clickUpdate: function () {
      console.log("수정 버튼 클릭");
      this.disabled = false;
    },
    saveUserInfo: function () {
      console.log("회원 정보 수정");
      this.disabled = true;
    },
    getProfile() {
      axios
        .post("https://j7a604.p.ssafy.io/molaeng/user", {
          userId: Number(this.userInfo.userId),
        })
        .then((res) => {
          this.userProfile.nickname = res.data.result.nickname;
          console.log("닉네임 불러옴vv");
        })
        .catch((error) => console.log(error));
    },
    updateProfile() {
      axios
        .put("https://j7a604.p.ssafy.io/molaeng/user", {
          gugunId: this.userProfile.gugunId,
          nickname: this.userProfile.nickname,
          userId: this.userProfile.nickname,
        })
        .then((res) => {
          console.log(res.data);
        })
        .catch((error) => console.log(error));
    },
    getGugunList() {
      this.SET_GUGUN;
      this.getGugunList();
      this.getSido();
    },
  },
  mounted() {
    this.getProfile();
    console.log("mounted 시작");
  },
  created() {
    this.getGugunList();
    console.log("created 시작");
  },
};
</script>

<style>
#buttonsId .v-btn--outlined {
  border: thin solid #fdac6d;
}
</style>
