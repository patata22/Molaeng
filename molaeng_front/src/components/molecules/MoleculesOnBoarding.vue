<template>
  <div id="background">
    <v-carousel
      cycle
      height="250px"
      hide-delimiter-background
      show-arrows-on-hover
      id="caro"
    >
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
<style>
#background {
  background-color: white;
  margin: 15px 5%;
  border-radius: 20px;
}
#caro {
  border-radius: 20px;
}
</style>
