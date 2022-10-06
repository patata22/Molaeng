<template>
  <!-- 이 페이지는 recipeId와 userId를 모두 더미(1)로 설정된 페이지이므로 추후 수정이 필요함 -->
  <div style="max-width: 420px" class="mx-auto">
    <v-divider></v-divider>
    <recipe-score></recipe-score>
    <v-divider></v-divider>
    <v-container>
      <v-row>
        <v-col class="ml-5 dark--text" cols="auto"><h2>리뷰</h2></v-col>
        <v-col cols="auto" align-self="center" class="semidark--text"
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
                        this.identifierId += 1;
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
                        this.identifierId += 1;
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
                        this.identifierId += 1;
                      }
                    "
                    >최신순</v-list-item-title
                  >
                </v-list-item>
                <v-list-item dense>
                  <v-list-item-title
                    class="primary--text"
                    v-on:click="
                      () => {
                        this.sort = 'like';
                        this.sortString = '좋아요순';
                        this.reviewList = [];
                        this.page = 0;
                        this.hasNext = true;
                        this.identifierId += 1;
                      }
                    "
                    >좋아요순</v-list-item-title
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

    <infinite-loading :identifier="identifierId" @infinite="getReview">
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
      <div v-else slot="no-results"></div>
      <div slot="no-more"></div>
    </infinite-loading>
    <!-- 여기서부터 리뷰 쓰기-> 뺄 수 있을지 모르겠다???? -->
    <div>
      <v-dialog v-if="userIdtmp > 0" v-model="writing">
        <template v-slot:activator="{ on, attrs }">
          <v-btn fab fixed right bottom color="carrot" v-bind="attrs" v-on="on">
            <v-icon color="white">mdi-pencil</v-icon>
          </v-btn>
        </template>
        <v-card style="margin: auto">
          <v-card-title> 리뷰 작성 </v-card-title>
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
                  no-resize
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
    </div>
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
      identifierId: 0,
      userIdtmp: this.$cookies.get("userId"),
    };
  },
  methods: {
    getReview($state) {
      var temp = this;
      var recipeId = window.location.pathname.split("/")[2];
      if (temp.sort == "like") {
        axios
          .get(
            "https://j7a604.p.ssafy.io/molaeng/review/like/" +
              recipeId +
              "?size=5&page=" +
              this.page,
            { withCredentials: true }
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
      } else {
        axios
          .get(
            "https://j7a604.p.ssafy.io/molaeng/review/" +
              recipeId +
              "?page=" +
              this.page +
              "&sort=" +
              this.sort +
              "&size=5",
            { withCredentials: true }
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
      }
    },
    sendReview() {
      var temp = this;
      var recipeId = window.location.pathname.split("/")[2];
      axios
        .post(
          "https://j7a604.p.ssafy.io/molaeng/review/" + recipeId,
          {
            userId: temp.userIdtmp,
            reviewContent: this.content,
            score: this.score,
          },
          { withCredentials: true }
        )
        .then(() => {
          temp.sort = "reviewDate,desc";
          temp.sortString = "최신순";
          temp.reviewList = [];
          temp.page = 0;
          temp.hasNext = true;
          temp.identifierId += 1;
          temp.writing = false;
          // this.$router.go();
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
