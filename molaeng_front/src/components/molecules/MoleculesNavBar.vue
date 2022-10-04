<template>
  <div :style="navStyle">
    <div class="navHeader mx-auto" style="max-width: 420px; min-height: 60px">
      <v-btn class="navLogo" text icon @click="goMain">
        <img height="44px" src="../../assets/navlogo.png" />
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
    },
    goMain() {
      this.$router.push("/").catch(() => {});
    },
    goMenu() {
      this.$router.push("/menu").catch(() => {});
    },
  },
};
</script>
<style>
.navHeader {
  display: flex;
  max-height: 10%;
  justify-content: space-between;
  align-items: center;
}
.navLogo {
  margin-left: 2%;
}
img {
  -webkit-user-drag: none;
}
</style>
