<template>
  <v-card outlined outline-color="primary">
    <v-row>
      <v-col cols="4" class="text-center">
        <br />
        <h2>평 점</h2>
        <br />
        <h2>{{ avgScore }}</h2>
        <br />
        <v-row>
          <v-spacer></v-spacer>
          <v-icon color="yellow" v-if="avgScore >= 1">mdi-star</v-icon>
          <v-icon color="yellow" v-else>mdi-star-outline</v-icon>
          <v-icon color="yellow" v-if="avgScore >= 2">mdi-star</v-icon>
          <v-icon color="yellow" v-else>mdi-star-outline</v-icon>
          <v-icon color="yellow" v-if="avgScore >= 3">mdi-star</v-icon>
          <v-icon color="yellow" v-else>mdi-star-outline</v-icon>
          <v-icon color="yellow" v-if="avgScore >= 4">mdi-star</v-icon>
          <v-icon color="yellow" v-else>mdi-star-outline</v-icon>
          <v-icon color="yellow" v-if="avgScore >= 5">mdi-star</v-icon>
          <v-icon color="yellow" v-else>mdi-star-outline</v-icon>
        </v-row>
      </v-col>
      <v-col cols="8">
        <v-row>
          <v-col cols="5">
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
          </v-col>
          <v-col cols="5" align-self="center">
            <v-progress-linear
              height="8"
              rounded
              background-color="semidark"
              color="primary"
              value="50"
            ></v-progress-linear>
          </v-col>
          <v-col cols="2">
            {{ scoreCnt[4] }}
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="5">
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
          </v-col>
          <v-col cols="5" align-self="center">
            <v-progress-linear
              height="8"
              rounded
              background-color="semidark"
              color="primary"
              value="50"
            ></v-progress-linear>
          </v-col>
          <v-col cols="2">
            {{ scoreCnt[3] }}
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="5">
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
          </v-col>
          <v-col cols="5" align-self="center">
            <v-progress-linear
              height="8"
              rounded
              background-color="semidark"
              color="primary"
              value="50"
            ></v-progress-linear>
          </v-col>
          <v-col cols="2">
            {{ scoreCnt[2] }}
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="5">
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
          </v-col>
          <v-col cols="5" align-self="center">
            <v-progress-linear
              height="8"
              rounded
              background-color="semidark"
              color="primary"
              value="50"
            ></v-progress-linear>
          </v-col>
          <v-col cols="2">
            {{ scoreCnt[1] }}
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="5">
            <v-icon color="yellow" small>mdi-star</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
            <v-icon color="yellow" small>mdi-star-outline</v-icon>
          </v-col>
          <v-col cols="5" align-self="center">
            <v-progress-linear
              height="8"
              rounded
              background-color="semidark"
              color="primary"
              value=""
            ></v-progress-linear>
          </v-col>
          <v-col cols="2">
            {{ scoreCnt[0] }}
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-card>
</template>
<script>
import axios from "axios";
export default {
  data: function () {
    return {
      recipeId: 1,
      scoreCnt: [0, 0, 0, 0, 0],
      avgScore: 0,
    };
  },
  created() {
    this.getScore();
  },
  methods: {
    getScore: function () {
      var temp = this;
      var url = "https://j7a604.p.ssafy.io/molaeng/review/score/";
      axios
        .get(url + temp.recipeId)
        // .get("https://j7a604.p.ssafy.io/molaeng/review/score/" + this.recipeId)
        .then(function (response) {
          temp.avgScore = response.data.result.avgScore;
          temp.scoreCnt = response.data.result.scoreCnt;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>
<style></style>
