<template>
  <div>
    <div>
      <h2>평점</h2>
      <h2>{{ avgScore }}</h2>
    </div>
    <div>
      <div>5점 {{ scoreCnt[4] }}</div>
      <div>4점 {{ scoreCnt[3] }}</div>
      <div>3점 {{ scoreCnt[2] }}</div>
      <div>2점 {{ scoreCnt[1] }}</div>
      <div>1점 {{ scoreCnt[0] }}</div>
    </div>
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
