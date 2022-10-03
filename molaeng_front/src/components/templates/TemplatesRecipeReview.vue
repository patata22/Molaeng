<template>
  <div>
    <recipe-review-card
      v-for="review in result.reviewList"
      :key="review.reviewId"
      :review="review"
    />
  </div>
</template>

<script>
import RecipeReviewCard from "@/components/organisms/OrganismsRecipeReviewCard.vue";
import axios from "axios";
export default {
  name: "RecipeReviewS",
  components: {
    RecipeReviewCard,
  },
  created() {
    this.getReview();
    this.getCount();
  },
  data: () => ({
    result: {
      page: 0,
      hasNext: true,
      reviewList: [],
    },
    recipeId: 1,
    page: 0,
    sort: "score,desc",
  }),
  methods: {
    getReview() {
      var temp = this;
      axios
        .get(
          "https://j7a604.p.ssafy.io/molaeng/review/" +
            this.recipeId +
            "?page=" +
            this.page +
            "&sort=" +
            this.sort +
            "&userId=1&size=5"
        )
        .then((response) => {
          this.result = response.data.result;
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
    sendReview() {
      var temp = this;
      console.log(temp.Content);
      axios
        .post("https://j7a604.p.ssafy.io/molaeng/review/" + this.recipeId, {
          userId: 1,
          reviewContent: this.content,
          score: this.score,
        })
        .then(() => {
          temp.writing = false;
          this.$router.go();
        })
        .catch((error) => console.log(error));
    },
    getCount() {
      var temp = this;
      axios
        .get("https://j7a604.p.ssafy.io/molaeng/review/count/" + this.recipeId)
        .then((response) => {
          temp.count = response.data.result;
        });
    },
  },
};
</script>

<style></style>
