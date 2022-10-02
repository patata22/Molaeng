<template>
  <div id="background" align="center" class="pa-4">
    <v-carousel
      cycle
      hide-delimiter-background
      show-arrows-on-hover
      id="caro"
      style="position: relative; height: 200px"
    >
      <v-carousel-item
        v-for="(item, i) in items"
        :key="i"
        :src="item.src"
        style="position: relative"
      >
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
  align-self: center;
  background-color: white;
  margin: auto;
  margin-bottom: 3%;
  max-width: 90%;
  border-radius: 20px;
}
#caro {
  border-radius: 20px;
}
</style>
