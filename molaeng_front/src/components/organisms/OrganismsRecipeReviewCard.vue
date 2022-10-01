<template>
  <v-card id="reviewCard" class="mx-auto rounded-lg" outlined max-width="351">
    <v-card-title class="pt-2 pb-0">
      <review-read-star :reviewScore="review.reviewScore" />
    </v-card-title>
    <v-card-text class="pb-12">
      <div id="writerInfo" class="font-weight-light">
        {{ review.userNickname }}님 / {{ review.reviewDate }}
      </div>
      <div class="dark--text text-body-1 mt-4">
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
        <span class="dark--text text-caption"> {{ review.likeCnt }} </span>
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
        .post("http://localhost:8080/molaeng/review/like", {
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
        .delete("http://localhost:8080/molaeng/review/like", {
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
