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
          ></v-select>
          <v-select
            placeholder="구/군"
            :items="gugun"
            outlined
            color="carrot"
            rounded
            dense
          ></v-select>
        </v-col>
      </v-row>
      <v-row class="px-15 justify-center">
        <v-btn
          class="primary font-weight-bold mt-5"
          rounded
          dense
          @click="submit"
        >
          수정
        </v-btn>
      </v-row>
      <v-row class="text--disabled justify-end pa-5 pt-15">
        <p class="font-weight-bold">탈퇴하기</p>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    sido: ["서울특별시"],
    gugun: [
      "강남구",
      "강동구",
      "강북구",
      "강서구",
      "관악구",
      "광진구",
      "구로구",
      "금천구",
      "노원구",
      "도봉구",
      "동대문구",
      "동작구",
      "마포구",
      "서대문구",
      "서초구",
      "성동구",
      "성북구",
      "송파구",
      "양천구",
      "영등포구",
      "용산구",
      "은평구",
      "종로구",
      "중구",
      "중랑구",
    ],
  }),
};
</script>

<style></style>
