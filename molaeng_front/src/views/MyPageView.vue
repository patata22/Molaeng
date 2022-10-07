<template>
  <div>
    <my-page-header :tabs="tabs"> </my-page-header>
    <router-view class="mx-auto" style="max-width: 420px"></router-view>
  </div>
</template>

<script>
import MyPageHeader from "../components/organisms/OrganismMyPageHeader.vue";

export default {
  name: "MyPageView",
  components: {
    MyPageHeader,
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
