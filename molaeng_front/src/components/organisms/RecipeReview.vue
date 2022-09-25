<template>
  <div>
    <recipe-score></recipe-score>
    <br />
    <review-card
      v-for="(review, i) in reviewList"
      :key="i"
      v-bind:review="review"
    />
    <button v-if="hasNext" v-on:click="getReview">더 보기</button>
  </div>
</template>
<script>
import RecipeScore from "../atoms/RecipeScore.vue";
import ReviewCard from "../molecules/ReviewCard.vue";
import axios from "axios";

export default {
  created() {
    this.getReview();
  },
  components: {
    RecipeScore,
    ReviewCard,
  },
  data: function () {
    return {
      recipeId: 1,
      hasNext: true,
      reviewList: [],
      page: 0,
    };
  },
  methods: {
    getReview() {
      var temp = this;
      axios
        .get(
          "http://localhost:8080/molaeng/review/" +
            this.recipeId +
            "?page=" +
            this.page +
            "&sort=score&userId=1&size=5"
        )
        .then(function (response) {
          console.log(response.data);
          temp.hasNext = response.data.result.hasNext;
          temp.page += 1;
          response.data.result.reviewList.forEach((e) => {
            temp.reviewList.push(e);
          });
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>
<style></style>
