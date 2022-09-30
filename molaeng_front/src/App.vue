<template>
  <v-app>
    <Nav-Bar :style="navStyle"></Nav-Bar>
    <router-view />
  </v-app>
</template>

<script>
import NavBar from "@/components/molecules/MoleculesNavBar.vue";
export default {
  name: "App",
  components: {
    NavBar,
  },
  data: () => ({
    navStyle: "background-color: #fef3c6;",
  }),
  computed: {
    path() {
      return this.$route.path;
    },
  },
  watch: {
    path(newPath) {
      console.log("path", newPath);
      if (newPath != "/") {
        this.navStyle = "";
      } else {
        this.navStyle = "background-color: #fef3c6;";
      }
    },
  },
};
</script>
<style scoped></style>
