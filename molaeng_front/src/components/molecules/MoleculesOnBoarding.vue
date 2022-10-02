<template>
  <div id="background">
    <v-carousel id="caro" cycle hide-delimiter-background show-arrows-on-hover>
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
        src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
      },
      {
        src: "https://cdn.vuetifyjs.com/images/carousel/sky.jpg",
      },
      {
        src: "https://cdn.vuetifyjs.com/images/carousel/bird.jpg",
      },
      {
        src: "https://cdn.vuetifyjs.com/images/carousel/planet.jpg",
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
  margin-top: 1%;
  margin-bottom: 4%;
  border-radius: 24px;
  padding: 16px;
  background-color: white;
  justify-content: center;
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
}
#caro {
  height: 200px !important;
  border-radius: 12px;
}
</style>
