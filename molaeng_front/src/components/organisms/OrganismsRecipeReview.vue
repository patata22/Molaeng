<template>
  <!-- 이 페이지는 recipeId와 userId를 모두 더미(1)로 설정된 페이지이므로 추후 수정이 필요함 -->
  <div>
    <v-divider></v-divider>
    <recipe-score></recipe-score>
    <br />
    <v-divider></v-divider>
    <v-container>
      <v-row>
        <v-col class="ml-5" cols="auto"><h2>리뷰</h2></v-col>
        <v-col cols="auto" align-self="center" class="dark--text"
          ><h4>{{ count }}건</h4></v-col
        >
        <v-spacer></v-spacer>
        <v-col cols="auto"
          ><div class="text-center mr-5">
            <v-menu offset-y>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="carrot"
                  dark
                  rounded
                  small
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon small>mdi-format-align-left</v-icon>&nbsp;{{
                    sortString
                  }}
                </v-btn>
              </template>
              <v-list>
                <v-list-item dense>
                  <v-list-item-title
                    class="primary--text"
                    v-on:click="
                      () => {
                        this.sort = 'score,desc';
                        this.sortString = '별점 높은순';
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
                        this.sortString = '별점 낮은순';
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
    <recipe-review-card
      v-for="(review, i) in reviewList"
      :key="i"
      v-bind:review="review"
    />
    <br />
    <br />
    <br />

    <infinite-loading @infinite="getReview">
      <div slot="no-more"></div>
    </infinite-loading>
    <!-- 여기서부터 리뷰 쓰기-> 뺄 수 있을지 모르겠다???? -->
    <v-row justify="center">
      <v-dialog
        transition="dialog-bottom-transition"
        v-model="writing"
        persistent
        max-width="600px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-footer fixed>
            <v-btn
              class="mx-2 mb-15"
              fab
              rounded
              color="carrot"
              v-bind="attrs"
              v-on="on"
              background-color="white"
              absolute
              right
            >
              <v-icon color="white">mdi-plus</v-icon>
            </v-btn>
          </v-footer>
        </template>
        <v-card>
          <v-card-title>
            <span class="text-h5">리뷰 작성</span>
          </v-card-title>
          <v-card-actions class="pt-0 pb-3">
            <v-rating
              large
              color="yellow"
              background-color="yellow"
              v-model="score"
            ></v-rating>
          </v-card-actions>
          <!-- 별점 -->
          <!-- 별점끝 -->
          <v-card-text>
            <v-row>
              <v-col cols="12" class="pb-0">
                <v-textarea
                  v-model="content"
                  label="리뷰를 작성해주세요"
                  outlined
                  required
                  hide-details
                  rows="4"
                ></v-textarea>
              </v-col>
            </v-row>
          </v-card-text>
          <v-card-actions class="mr-3">
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
  </div>
</template>
<script>
import InfiniteLoading from "vue-infinite-loading";
import RecipeScore from "@/components/molecules/MoleculesRecipeScore2";
import RecipeReviewCard from "@/components/organisms/OrganismsRecipeReviewCard.vue";
import axios from "axios";

export default {
  created() {
    this.getCount();
  },
  components: {
    RecipeScore,
    RecipeReviewCard,
    InfiniteLoading,
  },
  data: function () {
    return {
      hasNext: true,
      reviewList: [],
      page: 0,
      writing: false,
      content: "",
      score: 1,
      count: 0,
      sort: "score,desc",
      sortString: "별점 높은순",
    };
  },
  methods: {
    getReview($state) {
      var temp = this;
      var recipeId = window.location.pathname.split("/")[2];
      axios
        .get(
          "https://j7a604.p.ssafy.io/molaeng/review/" +
            recipeId +
            "?page=" +
            this.page +
            "&sort=" +
            this.sort +
            "&userId=1&size=5"
        )
        .then((response) => {
          response.data.result.reviewList.forEach((e) => {
            temp.reviewList.push(e);
          });
          temp.page += 1;
          if (response.data.result.hasNext) {
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    sendReview() {
      var temp = this;
      var recipeId = window.location.pathname.split("/")[2];
      console.log(temp.Content);
      axios
        .post("https://j7a604.p.ssafy.io/molaeng/review/" + recipeId, {
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
      var recipeId = window.location.pathname.split("/")[2];
      axios
        .get("https://j7a604.p.ssafy.io/molaeng/review/count/" + recipeId)
        .then((response) => {
          temp.count = response.data.result;
        });
    },
  },
};
</script>
<style></style>
