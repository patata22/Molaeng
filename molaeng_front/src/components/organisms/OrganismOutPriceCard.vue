<template>
  <v-card class="mx-auto mb-9 rounded-lg" v-show="this.seoul > 0">
    <v-card-title
      class="dark--text font-weight-bold"
      style="background-color: #fef3c6"
      text
    >
      <v-row>
        <v-col> 사먹을 때 가격 </v-col>
        <v-col style="font-size: medium; font-weight: 500; text-align: right">
          {{ seoul }}원
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
