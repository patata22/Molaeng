<template>
  <div>
    <recipe-review-card
      v-for="(review, i) in reviewList"
      :key="i"
      :review="review"
    ></recipe-review-card>
    <div
      v-if="reviewList.length == 0"
      slot="no-results"
      class="pa-5 font-weight-bold"
      style="font-size: 15px; color: #5b574b"
    >
      <v-row class="d-flex justify-center">
        <v-icon class="mb-1" style="font-size: 80px; color: #5b574b"
          >mdi-emoticon-cry-outline</v-icon
        ><br />
      </v-row>
      <v-row class="d-flex justify-center"> 아직 등록된 리뷰가 없어요 </v-row>
    </div>
    <infinite-loading @infinite="getMyReview">
      <div slot="no-results"></div>
      <div slot="no-more"></div>
    </infinite-loading>
  </div>
</template>
<script>
import RecipeReviewCard from "../organisms/OrganismsRecipeReviewCard.vue";
import InfiniteLoading from "vue-infinite-loading";
import axios from "axios";
export default {
  name: "MyReview",
  data: function () {
    return {
      reviewList: [],
      page: 0,
      size: 5,
      sort: "reviewDate,desc",
      hasNext: true,
    };
  },
  components: { RecipeReviewCard, InfiniteLoading },
  methods: {
    getMyReview($state) {
      var temp = this;
      axios
        .get("http://localhost:8080/molaeng/review", {
          params: {
            sort: temp.sort,
            page: temp.page,
            size: temp.size,
          },
          withCredentials: true,
        })
        .then((response) => {
          temp.page += 1;
          response.data.result.reviewList.forEach((review) => {
            temp.reviewList.push(review);
          });
          if (response.data.result.hasNext) {
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>
