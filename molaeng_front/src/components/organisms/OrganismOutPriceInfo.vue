<template>
  <div>
    <h1>{{ seoul }}</h1>
    <h1>{{ my }}</h1>
    <h1>{{ gugunName }}</h1>
  </div>
</template>

<script>
import API from "@/api/APIs";
const api = API;
export default {
  name: "OutPriceInfo",
  data() {
    return {
      seoul: 0,
      my: 0,
      gugunName: "",
    };
  },
  props: {
    recipeId: String,
  },
  created() {
    api.outPrice(this.recipeId).then((res) => {
      this.seoul = res.seoul;
      this.my = res.my;
      this.gugunName = res.gugunName;
    });
  },
};
</script>

<style></style>
