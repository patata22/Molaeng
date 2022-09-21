import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        carrot: "#ED8A53",
        primary: "#FDAC6D",
        secondary: "#FEE0A0",
        light: "#FEF3C6",
        forest: "#72A971",
        dark: "#5B574B",
        semidark: "#CECDC9",
      },
    },
  },
});
