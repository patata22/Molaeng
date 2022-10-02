<template>
  <v-card id="reviewCard" class="mx-auto rounded-lg" outlined max-width="351">
    <v-card-title class="pt-2 pb-0 d-flex justify-space-between">
      <review-read-star :reviewScore="review.reviewScore" />
      <span
        class="button font-weight-bold dark--text mr-1"
        style="font-size: 13px"
        @click="removeReview(1, review.reviewId)"
        >삭제</span
      >
    </v-card-title>
    <v-card-text class="pb-12">
      <div id="writerInfo" class="font-weight-light ml-1">
        {{ review.userNickname }}님 / {{ review.reviewDate.substr(0, 10) }}
      </div>
      <div class="dark--text text-body-1 mt-4 ml-1">
        {{ review.reviewContent }}
      </div>
      <v-btn
        absolute
        right
        icon
        class="flexcol"
        id="reviewLikeButton"
        color="primary"
        @click="toggleLike"
      >
        <v-icon
          >{{ isLiked ? "mdi-thumb-up" : "mdi-thumb-up-outline" }}
        </v-icon>
        <span class="dark--text text-caption" style="margin-top: -4px">
          {{ review.likeCnt }}
        </span>
      </v-btn>
    </v-card-text>
  </v-card>
</template>
<script>
import ReviewReadStar from "@/components/atoms/AtomsReviewReadStar.vue";
import axios from "axios";
export default {
  name: "RecipeReviewCard",
  components: {
    ReviewReadStar,
  },
  computed: {
    isLiked() {
      return this.review.liked;
    },
  },
  props: {
    review: Object,
  },
  data() {
    return {
      liked: this.review.liked,
    };
  },
  methods: {
    toggleLike() {
      if (this.review.liked) {
        this.dislikeReview(1, this.review.reviewId);
      } else {
        console.log(this.review.likeCnt);
        this.likeReview(1, this.review.reviewId);
      }
    },
    likeReview(userId, reviewId) {
      var temp = this;
      axios
        .post("https://j7a604.p.ssafy.io/molaeng/review/like", {
          userId: userId,
          reviewId: reviewId,
        })
        .then(function () {
          console.log("like!");
          console.log("after!");
          console.log(temp.review.likeCnt);
          if (!temp.review.liked) {
            temp.review.likeCnt += 1;
            temp.review.liked = true;
          }
        })
        .catch((error) => console.log(error));
    },
    dislikeReview(userId, reviewId) {
      var temp = this;
      axios
        .delete("https://j7a604.p.ssafy.io/molaeng/review/like", {
          data: { userId: userId, reviewId: reviewId },
        })
        .then(function () {
          console.log("dislike!");
          if (temp.review.liked) {
            temp.review.likeCnt -= 1;
            temp.review.liked = false;
          }
        })
        .catch((error) => console.log(error));
    },
    removeReview(userId, reviewId) {
      console.log(reviewId);
      axios
        .delete("https://j7a604.p.ssafy.io/molaeng/review", {
          data: { userId: userId, reviewId: reviewId },
        })
        .then((response) => {
          console.log(response);
          this.$router.go();
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>

<style>
#writerInfo {
  color: #adaba5;
}
#reviewLikeButton .v-btn__content {
  flex-direction: column;
}
#reviewCard {
  border: 3px solid #fdac6d;
  box-shadow: 0px 4px 10px #feefe2;
}
</style>
