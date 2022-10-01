<template>
  <div>
    <recipe-review-card
      v-for="(review, i) in reviewList"
      :key="i"
      :review="review"
    ></recipe-review-card>
    <v-btn v-if="hasNext" @click="getMyReview">더 보기</v-btn>
  </div>
</template>
<script>
import RecipeReviewCard from "../organisms/OrganismsRecipeReviewCard.vue";
import axios from "axios";
export default {
  name: "MyReview",
  created() {
    this.getMyReview();
  },
  data: function () {
    return {
      userId: 1,
      reviewList: [],
      page: 0,
      size: 5,
      sort: "reviewDate,desc",
      hasNext: true,
    };
  },
  components: { RecipeReviewCard },
  methods: {
    getMyReview() {
      var temp = this;
      axios
        .get("http://j7a604.p.ssafy.io:8080/molaeng/review", {
          params: {
            userId: temp.userId,
            sort: temp.sort,
            page: temp.page,
            size: temp.size,
          },
        })
        .then((response) => {
          temp.hasNext = response.data.result.hasNext;
          temp.page += 1;
          response.data.result.reviewList.forEach((review) => {
            temp.reviewList.push(review);
          });
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>
