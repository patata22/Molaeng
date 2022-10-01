<template>
  <div class="d-flex" id="header" :style="navStyle">
    <v-btn class="ma-2" text icon color="dark" @click="goMain">
      <v-icon x-large>mdi-penguin</v-icon>
    </v-btn>
    <div absolute right class="d-flex justify-end ma-2">
      <v-btn
        text
        icon
        color="dark"
        class="ma-1"
        @click="goSearch"
        v-if="!isSearchPage"
      >
        <v-icon large>mdi-magnify</v-icon>
      </v-btn>
      <v-btn text icon color="dark" class="ma-1" @click="goMenu">
        <v-icon large>mdi-menu</v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  computed: {
    path() {
      return this.$route.path;
    },
    isSearchPage() {
      if (this.path.startsWith("/search")) {
        return true;
      } else return false;
    },
    navStyle() {
      if (this.path != "/") {
        return "";
      } else {
        return "background-color: #fef3c6;";
      }
    },
  },
  methods: {
    goSearch() {
      this.$router.push("/search").catch(() => {});
      console.log("검색 페이지로 이동합니다.");
    },
    goMain() {
      this.$router.push("/").catch(() => {});
      console.log("메인 페이지로 이동합니다.");
    },
    goMenu() {
      this.$router.push("/menu").catch(() => {});
      console.log("메뉴로 이동합니다.");
    },
  },
};
</script>
<style scoped>
#header {
  justify-content: space-between;
  max-height: 10%;
}
</style>
