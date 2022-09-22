<template>
  <div>
    <div class="dark--text font-weight-bold text-center pa-5">
      <h2>마이 페이지</h2>
    </div>
    <v-tabs centered fixed-tabs>
      <menu-tab-item
        v-for="(title, index) in titles"
        :key="index"
        :text="title"
        :requestUrl="title"
      />
    </v-tabs>
  </div>
</template>

<script>
import MenuTabItem from "../atoms/AtomsMenuTabItem.vue";
export default {
  name: "MyPageTab",
  components: {
    MenuTabItem,
  },
  data: () => ({
    titles: ["프로필 수정", "내가 쓴 리뷰"],
  }),
};
</script>

<style></style>
