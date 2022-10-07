<template>
  <v-app>
    <Nav-Bar></Nav-Bar>
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
};
</script>
<style scoped></style>
