<template>
  <div></div>
</template>

<script>
import KakaoAPI from "@/api/KakaoAPI";
const kakao = KakaoAPI;
export default {
  async created() {
    this.serverLogin(await kakao.KakaoLogin());
  },
  methods: {
    serverLogin() {
      this.$router.replace("");
    },
  },
};
</script>

<style></style>
