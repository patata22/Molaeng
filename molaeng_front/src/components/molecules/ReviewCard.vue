<template>
  <div>
    <v-card max-width="350px">
      <v-card-title>
        <v-row>
          <atom-review-read-star
            v-for="(score, i) in [1, 2, 3, 4, 5]"
            :key="i"
            v-bind:score="score"
            v-bind:reviewScore="review.reviewScore"
          ></atom-review-read-star>
        </v-row>
      </v-card-title>
      <v-row>
        {{ review.userNickname }} / {{ review.reviewDate.substr(0, 10) }}
      </v-row>
      <v-row>
        <div>{{ review.reviewContent }}</div>
      </v-row>
      <v-btn
        v-if="review.liked"
        v-on:click="dislikeReview(1, review.reviewId)"
        icon
        right
        color="carrot"
      >
        <v-icon>mdi-thumb-up</v-icon>
      </v-btn>
      <v-btn
        v-else
        v-on:click="likeReview(1, review.reviewId)"
        icon
        right
        color="semidark"
      >
        <v-icon>mdi-thumb-up</v-icon>
      </v-btn>
      <div>{{ review.likeCnt }}</div>
    </v-card>
    <br />
  </div>
</template>
<script>
import axios from "axios";
import AtomReviewReadStar from "../atoms/AtomReviewReadStar.vue";
export default {
  components: {
    AtomReviewReadStar,
  },
  props: { review: { type: Object } },
  methods: {
    likeReview: function (userId, reviewId) {
      var temp = this;
      axios
        .post("http://localhost:8080/molaeng/review/like", {
          userId: userId,
          reviewId: reviewId,
        })
        .then(function () {
          temp.review.likeCnt += 1;
          temp.review.liked = true;
        })
        .catch((error) => console.log(error));
    },
    dislikeReview: function (userId, reviewId) {
      var temp = this;
      axios
        .delete("http://localhost:8080/molaeng/review/like", {
          data: { userId: userId, reviewId: reviewId },
        })
        .then(function () {
          temp.review.likeCnt -= 1;
          temp.review.liked = false;
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>
<style></style>
