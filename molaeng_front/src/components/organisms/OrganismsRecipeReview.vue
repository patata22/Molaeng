<template>
  <div>
    <v-container>
      <v-card>
        <recipe-score></recipe-score>
      </v-card>
    </v-container>
    <br />
    <v-container>
      <v-row>
        <h3>리뷰</h3>
        <h5>리뷰 숫자</h5>
      </v-row>
    </v-container>
    <review-card
      v-for="(review, i) in reviewList"
      :key="i"
      v-bind:review="review"
    />
    <button v-if="hasNext" v-on:click="getReview">더 보기</button>
    <!-- 여기서부터 리뷰 쓰기-> 뺄 수 있을지 모르겠다???? -->
    <v-row justify="center">
      <v-dialog
        transition="dialog-bottom-transition"
        v-model="writing"
        persistent
        max-width="600px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            right
            class="mx-2"
            fab
            rounded
            color="primary"
            v-bind="attrs"
            v-on="on"
          >
            <v-icon dark>mdi-plus</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="text-h5">리뷰 작성</span>
          </v-card-title>
          <!-- 별점 -->
          <v-row>
            <v-col>
              <div v-if="score >= 1">
                <v-icon
                  color="yellow"
                  v-on:click="
                    () => {
                      score = 1;
                    }
                  "
                  >mdi-star</v-icon
                >
              </div>
              <div v-else>
                <v-icon
                  color="primary"
                  v-on:click="
                    () => {
                      score = 1;
                    }
                  "
                  outlined
                  >mdi-star-outline</v-icon
                >
              </div>
            </v-col>
            <v-col>
              <div v-if="score >= 2">
                <v-icon
                  color="yellow"
                  v-on:click="
                    () => {
                      score = 2;
                    }
                  "
                  >mdi-star</v-icon
                >
              </div>
              <div v-else>
                <v-icon
                  color="primary"
                  v-on:click="
                    () => {
                      score = 2;
                    }
                  "
                  outlined
                  >mdi-star-outline</v-icon
                >
              </div>
            </v-col>
            <v-col>
              <div v-if="score >= 3">
                <v-icon
                  color="yellow"
                  v-on:click="
                    () => {
                      score = 3;
                    }
                  "
                  >mdi-star</v-icon
                >
              </div>
              <div v-else>
                <v-icon
                  color="primary"
                  outlined
                  v-on:click="
                    () => {
                      score = 3;
                    }
                  "
                  >mdi-star-outline</v-icon
                >
              </div>
            </v-col>
            <v-col>
              <div v-if="score >= 4">
                <v-icon
                  color="yellow"
                  v-on:click="
                    () => {
                      score = 4;
                    }
                  "
                  >mdi-star</v-icon
                >
              </div>
              <div v-else>
                <v-icon
                  color="primary"
                  outlined
                  v-on:click="
                    () => {
                      score = 4;
                    }
                  "
                  >mdi-star-outline</v-icon
                >
              </div>
            </v-col>
            <v-col>
              <div v-if="score >= 5">
                <v-icon
                  color="yellow"
                  v-on:click="
                    () => {
                      score = 5;
                    }
                  "
                  >mdi-star</v-icon
                >
              </div>
              <div v-else>
                <v-icon
                  color="primary"
                  outlined
                  v-on:click="
                    () => {
                      score = 5;
                    }
                  "
                  >mdi-star-outline</v-icon
                >
              </div>
            </v-col>
          </v-row>
          <!-- 별점끝 -->
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="content"
                    label="리뷰를 작성해주세요"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn outlined color="primary" @click="writing = false">
              취소
            </v-btn>
            <v-btn color="primary" white--text @click="sendReview">
              등록
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    <!--  -->
  </div>
</template>
<script>
import RecipeScore from "../molecules/MoleculesRecipeScore.vue";
import ReviewCard from "../molecules/MoleculesReviewCard.vue";
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
      writing: false,
      content: "",
      score: 1,
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
        .post("http://localhost:8080/molaeng/review/" + this.recipeId, {
          userId: 1,
          reviewContent: this.content,
          score: this.score,
        })
        .then((request) => {
          console.log(request);
          temp.writing = false;
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>
<style></style>
