<template>
  <div>
    <h3>비로그인 유저 메뉴 페이지입니다!</h3>
  </div>
</template>
<script>
export default {
  name: "UserMenu",
  components: {},
  data: () => ({
    userInfo: {
      name: "김펭귄",
    },
  }),
  methods: {
    exit() {
      this.$router.push("/").catch(() => {}); // 임시로 mainPage로 이동하게 해둠. 어떻게 할지 물어봐야 함.
    },
  },
};
</script>
<style></style>
