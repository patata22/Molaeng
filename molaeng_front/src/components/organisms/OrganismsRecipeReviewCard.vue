<template>
  <v-card
    id="reviewCard"
    class="mx-auto rounded-xl mt-2 mb-8"
    max-width="420"
    outlined
  >
    <v-card-title class="pt-2 pb-0 d-flex justify-space-between">
      <review-read-star :reviewScore="review.reviewScore" />
      <span
        v-if="userId == review.userId"
        class="button font-weight-bold dark--text mr-1"
        style="font-size: 13px"
        @click="removeReview(userId, review.reviewId)"
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
    <v-snackbar
      color="carrot"
      rounded="pill"
      text
      centered
      v-model="snackbar"
      timeout="1500"
    >
      로그인이 필요한 기능입니다!
    </v-snackbar>
  </v-card>
</template>
<script>
import ReviewReadStar from "@/components/atoms/AtomsReviewReadStar.vue";
import axios from "axios";
export default {
  created() {},
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
      userId: this.$cookies.get("userId"),
      snackbar: false,
    };
  },
  methods: {
    toggleLike() {
      if (!this.userId) {
        this.snackbar = true;
      } else {
        if (this.review.liked) {
          this.dislikeReview(this.userId, this.review.reviewId);
        } else {
          this.likeReview(this.userId, this.review.reviewId);
        }
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
          if (temp.review.liked) {
            temp.review.likeCnt -= 1;
            temp.review.liked = false;
          }
        })
        .catch((error) => console.log(error));
    },
    removeReview(userId, reviewId) {
      axios
        .delete("https://j7a604.p.ssafy.io/molaeng/review", {
          data: { userId: userId, reviewId: reviewId },
        })
        .then(() => {
          this.$router.go();
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>

<style>
#writerInfo {
  color: rgb(91 87 75 / 60%);
}
#reviewLikeButton .v-btn__content {
  flex-direction: column;
}
#reviewCard {
  border: 3px solid #fdac6d;
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
}
</style>
