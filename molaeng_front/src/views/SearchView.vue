<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "SearchView",
  components: {},
  data: () => ({
    buttonText: "레시피 검색",
    tabs: [
      {
        tabName: "재료",
        tabLink: "/search/ingredient",
      },
      {
        tabName: "레시피",
        tabLink: "/search/recipe",
      },
    ],
  }),
};
</script>

<style>
#content {
  padding-bottom: 52px;
  overflow: auto;
}
</style>
