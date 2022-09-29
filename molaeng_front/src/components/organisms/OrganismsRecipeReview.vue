<template>
  <!-- 이 페이지는 recipeId와 userId를 모두 더미(1)로 설정된 페이지이므로 추후 수정이 필요함 -->
  <div>
    <v-container>
      <v-card outlined class="outline-color=primary">
        <recipe-score></recipe-score>
      </v-card>
    </v-container>
    <br />
    <v-container>
      <v-row>
        <v-col cols="2"><h3>리뷰</h3></v-col>
        <v-col cols="3" align-self="center" class="dark--text"
          ><h4>{{ count }}건</h4></v-col
        >
        <v-spacer></v-spacer>
        <v-col cols="4" align-self="center"
          ><div class="text-center">
            <v-menu offset-y>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="primary"
                  dark
                  rounded
                  small
                  v-bind="attrs"
                  v-on="on"
                >
                  {{ sortString }}
                </v-btn>
              </template>
              <v-list>
                <v-list-item dense>
                  <v-list-item-title
                    class="primary--text"
                    v-on:click="
                      () => {
                        this.sort = 'score,desc';
                        this.sortString = '별점 높은 순';
                        this.reviewList = [];
                        this.page = 0;
                        this.hasNext = true;
                        getReview();
                      }
                    "
                    >별점 높은순</v-list-item-title
                  >
                </v-list-item>
                <v-list-item dense>
                  <v-list-item-title
                    class="primary--text"
                    v-on:click="
                      () => {
                        this.sort = 'score';
                        this.sortString = '별점 낮은 순';
                        this.reviewList = [];
                        this.page = 0;
                        this.hasNext = true;
                        getReview();
                      }
                    "
                    >별점 낮은순</v-list-item-title
                  >
                </v-list-item>
                <v-list-item dense>
                  <v-list-item-title
                    class="primary--text"
                    v-on:click="
                      () => {
                        this.sort = 'reviewDate,desc';
                        this.sortString = '최신순';
                        this.reviewList = [];
                        this.page = 0;
                        this.hasNext = true;
                        getReview();
                      }
                    "
                    >최신순</v-list-item-title
                  >
                </v-list-item>
              </v-list>
            </v-menu>
          </div></v-col
        >
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
            class="mx-2"
            fab
            rounded
            color="primary"
            v-bind="attrs"
            v-on="on"
            absolute
            right
          >
            <v-icon dark>mdi-plus</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="text-h5">&nbsp;&nbsp;리뷰 작성</span>
          </v-card-title>
          <!-- 별점 -->
          <v-row align-content="center">
            <v-col cols="1"></v-col>
            <v-col cols="2">
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
            <v-col cols="2">
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
            <v-col cols="2">
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
            <v-col cols="2">
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
            <v-col cols="2">
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
            <hr />
          </v-row>
          <!-- 별점끝 -->
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-textarea
                    v-model="content"
                    label="리뷰를 작성해주세요"
                    outlined
                    required
                  ></v-textarea>
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
    this.getCount();
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
      count: 0,
      sort: "score,desc",
      sortString: "별점 높은 순",
    };
  },
  methods: {
    getReview() {
      var temp = this;
      axios
        .get(
          "http://j7a604.p.ssafy.io:8080/molaeng/review/" +
            this.recipeId +
            "?page=" +
            this.page +
            "&sort=" +
            this.sort +
            "&userId=1&size=5",
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
        .post("http://j7a604.p.ssafy.io:8080/molaeng/review/" + this.recipeId, {
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
        .get(
          "http://j7a604.p.ssafy.io:8080/molaeng/review/count/" + this.recipeId,
        )
        .then((response) => {
          temp.count = response.data.result;
        });
    },
  },
};
</script>
<style></style>
