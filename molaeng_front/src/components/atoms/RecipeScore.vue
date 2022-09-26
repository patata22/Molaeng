<template>
  <div>
    <v-row>
      <v-col>
        <h2>평점</h2>
        <h2>{{ avgScore }}</h2>
      </v-col>
      <div>
        <v-row>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            {{ scoreCnt[4] }}
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>{{ scoreCnt[3] }}</v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>{{ scoreCnt[2] }}</v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>{{ scoreCnt[1] }}</v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-icon color="yellow">mdi-star</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>
            <v-icon color="yellow">mdi-star-outline</v-icon>
          </v-col>
          <v-col>{{ scoreCnt[0] }}</v-col>
        </v-row>
      </div>
    </v-row>
  </div>
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
      var url = "http://localhost:8080/molaeng/review/score/";
      axios
        .get(url + temp.recipeId)
        // .get("http://j7a604.p.ssafy.io/molaeng/review/score/" + this.recipeId)
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
