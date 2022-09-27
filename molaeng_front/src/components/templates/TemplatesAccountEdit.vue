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
      <v-row class="px-15">
        <v-col>
          <div class="mb-1">
            <h4 class="carrot--text">닉네임</h4>
          </div>
          <v-text-field
            placeholder="닉네임을 입력해주세요."
            outlined
            color="carrot"
            rounded
            dense
            v-model="nickname"
            :error-messages="nicknameDuplicated"
            :disabled="validated ? '' : disabled"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row class="px-15">
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
      <v-row class="px-15 justify-center">
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
          @click="saveUserInfo"
          v-if="disabled === false"
        >
          저장
        </v-btn>
      </v-row>
      <v-row class="text--disabled justify-end pa-5 pt-15">
        <p class="font-weight-bold">탈퇴하기</p>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
// import api from "@api/RestAPI";

export default {
  name: "myPage",
  mounted() {
    this.gugun = this.$store.getters.getAllGugun;
  },
  data: () => ({
    sido: ["서울특별시"],
    gugun: [],
    nickname: "기본 닉네임",
    myRegion: "",
    userId: 1,
    disabled: true,
  }),

  computed: { ...mapGetters(["userId", "nickname", "myRegion", "gugun"]) },
  methods: {
    ...mapActions(["getUserInfo"]),
    clickUpdate: function () {
      console.log("수정 버튼 클릭");
      this.disabled = false;
    },
    saveUserInfo: function () {
      console.log("회원 정보 수정");
      this.disabled = true;
    },
  },
  created() {
    console.log("시작한다1");
    // this.getUserInfo(this.userId);
    this.$store.dispatch("getUserInfo");
    console.log("created 끝?");
  },
};
</script>

<style></style>
