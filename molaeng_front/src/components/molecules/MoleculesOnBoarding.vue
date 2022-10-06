<template>
  <div id="background">
    <v-carousel height="200" cycle hide-delimiters show-arrows-on-hover>
      <v-carousel-item v-for="(item, i) in items" :key="i" :src="item.src">
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
export default {
  data: () => ({
    items: [
      {
        src: require("../../assets/help1.png"),
      },
      {
        src: require("../../assets/help2.png"),
      },
      {
        src: require("../../assets/help3.png"),
      },
      {
        src: require("../../assets/help4.png"),
      },
      {
        src: require("../../assets/help5.png"),
      },
      {
        src: require("../../assets/help6.png"),
      },
    ],
  }),
};
</script>
<style scoped>
#background {
  display: flex;
  max-width: 90%;
  margin: auto;
  margin-bottom: 2%;
  border-radius: 24px;
  padding: 16px;
  background-color: white;
  justify-content: center;
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
}
</style>
