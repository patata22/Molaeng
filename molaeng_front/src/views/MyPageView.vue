<template>
  <div>
    <nav-bar></nav-bar>
    <my-page-header :tabs="tabs"> </my-page-header>
    <router-view></router-view>
  </div>
</template>

<script>
import MyPageHeader from "../components/organisms/OrganismMyPageHeader.vue";
// import AccountEdit from "../components/templates/TemplatesEditProfile.vue";
import NavBar from "../components/molecules/MoleculesNavBar.vue";

export default {
  name: "MyPageView",
  components: {
    MyPageHeader,
    // AccountEdit,
    NavBar,
  },
  data: () => ({
    buttonText: "마이 페이지",
    tabs: [
      {
        tabName: "프로필 수정",
        tabLink: "/myPage/editProfile",
      },
      {
        tabName: "내가 쓴 리뷰",
        tabLink: "/myPage/myReview",
      },
    ],
  }),
};
</script>
