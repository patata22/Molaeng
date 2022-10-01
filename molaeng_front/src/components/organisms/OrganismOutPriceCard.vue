<template>
  <div>
    <v-card class="mx-auto mb-9 rounded-lg">
      <v-card-title
        class="dark--text font-weight-bold"
        style="background-color: #fef3c6"
        text
      >
        <v-row>
          <v-col> 사먹을 때 가격 </v-col>
          <v-col style="font-size: medium; font-weight: 500; text-align: right">
            {{ price }}원
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text style="padding: 0; text-align: center">
        <out-price-graph
          :seoul="seoul"
          :gugunName="gugunName"
          :my="my"
          :recipePrice="recipePrice"
        />
      </v-card-text>
    </v-card>
    <div align="center" v-if="seoul > 0" class="font-weight-bold text-h6 mb-3">
      <div>{{ compareLocation }}에서는 만들어 먹는게</div>
      <div :style="compareStyle">
        {{ compareAbsPrice }}원 더 {{ compareText }}
      </div>
    </div>
  </div>
</template>

<script>
import API from "@/api/APIs";
import OutPriceGraph from "@/components/organisms/OrganismsOutPriceGraph.vue";
const api = API;

export default {
  name: "OutPriceInfo",
  components: {
    OutPriceGraph,
  },
  data() {
    return {
      res: {
        seoul: 0,
        my: 0,
        gugunName: "",
      },
      recipePrice: 5000,
    };
  },
  computed: {
    seoul() {
      return this.res.seoul;
    },
    my() {
      return this.res.my;
    },
    gugunName() {
      return this.res.gugunName;
    },
    price() {
      if (this.seoul > 0) {
        return this.seoul;
      } else return "-";
    },
    compareLocation() {
      if (this.my > 0) {
        return this.gugunName;
      } else return "서울";
    },
    comparePrice() {
      if (this.compareLocation == "서울") {
        return this.seoul - this.recipePrice;
      } else return this.my - this.recipePrice;
    },
    compareText() {
      if (this.comparePrice > 0) {
        return "싸네요";
      } else {
        return "비싸네요";
      }
    },
    compareAbsPrice() {
      return Math.abs(this.comparePrice);
    },
    compareStyle() {
      if (this.comparePrice > 0) {
        return "color:#72A971";
      } else return "color:#ED8A53";
    },
  },
  props: {
    recipeId: String,
  },
  async created() {
    this.res = await api.outPrice(this.recipeId);
  },
};
</script>

<style></style>
