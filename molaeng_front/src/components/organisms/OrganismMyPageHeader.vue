<template>
  <div class="mx-auto" style="max-width: 420px">
    <div class="dark--text font-weight-bold text-center py-5">
      <h1>마이 페이지</h1>
    </div>
    <v-tabs fixed-tabs>
      <menu-tab-item v-for="(tab, index) in tabs" :key="index" :tab="tab" />
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
  props: {
    tabs: Array,
  },
};
</script>
