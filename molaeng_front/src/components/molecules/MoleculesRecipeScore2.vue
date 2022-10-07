<template>
  <v-card
    class="rounded-xl"
    style="box-shadow: none; margin: auto; margin-top: 8%; margin-bottom: 8%"
    max-width="351px"
  >
    <v-row no-gutters class="mt-2 justify-start">
      <v-col cols="4" class="mt-1 ml-1">
        <div class="d-flex justify-center align-center">
          <span class="text-weight-black dark--text" style="font-weight: 600"
            >평균별점</span
          >
        </div>
        <div class="d-flex justify-center">
          <span class="dark--text text-h3 mt-1" style="font-weight: 600">{{
            avgScore
          }}</span>
        </div>
        <div class="d-flex justify-center align-center mt-1">
          <v-rating
            :value="parseFloat(avgScore)"
            readonly
            color="yellow"
            background-color="yellow"
            size="20"
          ></v-rating>
        </div>
      </v-col>
      <v-col cols="7" class="pl-3 pr-1">
        <v-row
          class="d-flex align-center justify-center"
          style="height: 22px; width: 220px"
        >
          <v-col cols="4">
            <v-rating
              :value="5"
              readonly
              color="yellow"
              background-color="yellow"
              x-small
            ></v-rating>
          </v-col>
          <v-col cols="6" class="pr-1">
            <v-progress-linear
              background-color="semidark"
              color="primary"
              rounded
              height="6"
              :value="this.ratio[4]"
            ></v-progress-linear>
          </v-col>
          <v-spacer />
          <v-col class="pl-1 mr-4" cols="auto">
            <span style="font-size: 0.9rem; color: #5b574b">{{
              scoreCnt[4]
            }}</span>
          </v-col>
        </v-row>
        <v-row
          class="d-flex align-center justify-center"
          style="height: 22px; width: 220px"
        >
          <v-col cols="4">
            <v-rating
              :value="4"
              readonly
              color="yellow"
              background-color="yellow"
              x-small
            ></v-rating>
          </v-col>
          <v-col cols="6" class="pr-1">
            <v-progress-linear
              background-color="semidark"
              color="primary"
              rounded
              height="6"
              :value="this.ratio[3]"
            ></v-progress-linear>
          </v-col>
          <v-spacer />
          <v-col class="pl-1 mr-4" cols="auto">
            <span style="font-size: 0.9rem; color: #5b574b">{{
              scoreCnt[3]
            }}</span>
          </v-col>
        </v-row>
        <v-row
          class="d-flex align-center justify-center"
          style="height: 22px; width: 220px"
        >
          <v-col cols="4">
            <v-rating
              :value="3"
              readonly
              color="yellow"
              background-color="yellow"
              x-small
              rounded
              height="6"
            ></v-rating>
          </v-col>
          <v-col cols="6" class="pr-1">
            <v-progress-linear
              background-color="semidark"
              color="primary"
              rounded
              height="6"
              :value="this.ratio[2]"
            ></v-progress-linear>
          </v-col>
          <v-spacer />
          <v-col class="pl-1 mr-4" cols="auto">
            <span style="font-size: 0.9rem; color: #5b574b">{{
              scoreCnt[2]
            }}</span>
          </v-col>
        </v-row>
        <v-row
          class="d-flex align-center justify-center"
          style="height: 22px; width: 220px"
        >
          <v-col cols="4">
            <v-rating
              :value="2"
              readonly
              color="yellow"
              background-color="yellow"
              x-small
              rounded
            ></v-rating>
          </v-col>
          <v-col cols="6" class="pr-1">
            <v-progress-linear
              background-color="semidark"
              color="primary"
              rounded
              height="6"
              :value="this.ratio[1]"
            ></v-progress-linear>
          </v-col>
          <v-spacer />
          <v-col class="pl-1 mr-4" cols="auto">
            <span style="font-size: 0.9rem; color: #5b574b">{{
              scoreCnt[1]
            }}</span>
          </v-col>
        </v-row>
        <v-row
          class="d-flex align-center justify-center"
          style="height: 22px; width: 220px"
        >
          <v-col cols="4">
            <v-rating
              :value="1"
              readonly
              color="yellow"
              background-color="yellow"
              x-small
              rounded
            ></v-rating>
          </v-col>
          <v-col cols="6" class="pr-1">
            <v-progress-linear
              background-color="semidark"
              color="primary"
              rounded
              height="6"
              :value="this.ratio[0]"
            ></v-progress-linear>
          </v-col>
          <v-spacer />
          <v-col class="pl-1 mr-4" cols="auto">
            <span style="font-size: 0.9rem; color: #5b574b">{{
              scoreCnt[0]
            }}</span>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-card>
</template>
<script>
import axios from "axios";
export default {
  name: "RecipeScore",
  computed: {
    total() {
      return (
        this.scoreCnt[0] +
        this.scoreCnt[1] +
        this.scoreCnt[2] +
        this.scoreCnt[3] +
        this.scoreCnt[4]
      );
    },
    ratio() {
      return [
        (this.scoreCnt[0] / this.total) * 100,
        (this.scoreCnt[1] / this.total) * 100,
        (this.scoreCnt[2] / this.total) * 100,
        (this.scoreCnt[3] / this.total) * 100,
        (this.scoreCnt[4] / this.total) * 100,
      ];
    },
  },
  data: function () {
    return {
      scoreCnt: [0, 0, 0, 0, 0],
      avgScore: 0.0,
    };
  },
  created() {
    this.getScore();
  },
  methods: {
    getScore() {
      var recipeId = window.location.pathname.split("/")[2];
      var temp = this;
      axios
        .get("https://j7a604.p.ssafy.io/molaeng/review/score/" + recipeId)
        .then((response) => {
          temp.scoreCnt = response.data.result.scoreCnt;
          temp.avgScore = response.data.result.avgScore.toFixed(1);
        })
        .catch();
    },
  },
};
</script>
<style></style>
