<template>
  <div>
    <nav-bar></nav-bar>
    <my-page-header> </my-page-header>
    <account-edit> </account-edit>
  </div>
</template>

<script>
import MyPageHeader from "../components/organisms/OrganismMyPageHeader.vue";
import AccountEdit from "../components/templates/TemplatesAccountEdit.vue";
import NavBar from "../components/molecules/MoleculesNavBar.vue";

export default {
  name: "MyPageView",
  components: {
    MyPageHeader,
    AccountEdit,
    NavBar,
  },
  data() {
    return {};
  },
};
</script>
