<template>
  <div class="background">
    <!-- <member-menu></member-menu> -->
    <exit-button></exit-button>
    <router-view></router-view>
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
    return {};
  },
};
</script>
<style>
.background {
  background-color: #fef3c6;
  width: 100%;
  height: 100%;
}
</style>
