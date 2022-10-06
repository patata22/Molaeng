<template>
  <div class="background">
    <div class="mx-auto" style="max-width: 420px">
      <!-- <member-menu></member-menu> -->
      <exit-button :url="backURL"></exit-button>
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
// import MemberMenu from "@/components/templates/TemplatesMemberMenu.vue";
import exitButton from "@/components/atoms/AtomExitButton.vue";

export default {
  name: "MenuView",
  components: {
    exitButton,
    // MemberMenu,
  },
  data() {
    return {
      backURL: "",
    };
  },
  created() {
    this.backURL = this.$route.params.url;
  },
};
</script>
<style scoped>
.background {
  position: absolute;
  background-color: #fef3c6;
  width: 100%;
  height: 100%;
}
</style>
