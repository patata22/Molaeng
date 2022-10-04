<template lang="">
  <v-container>
    <div class="d-flex justify-center align center">
      <h1 class="dark--text">레시피 목록</h1>
    </div>
    <div class="pt-5 mx-auto" style="width: 86%; max-width: 351px">
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="carrot"
            dark
            rounded
            small
            v-bind="attrs"
            v-on="on"
            depressed
            style="float: right"
          >
            {{ sortString }}
          </v-btn>
        </template>
        <v-list>
          <v-list-item dense>
            <v-list-item-title
              class="carrot--text"
              v-on:click="
                () => {
                  this.sortString = '재료 일치순';
                  this.recipeList = [];
                  this.page = 0;
                  this.hasNext = true;
                  this.sort = 0;
                  getRecipeByIngredient();
                }
              "
              >재료 일치순</v-list-item-title
            >
          </v-list-item>
          <v-list-item dense>
            <v-list-item-title
              class="carrot--text"
              v-on:click="
                () => {
                  this.sortString = '비용 낮은순';
                  this.recipeList = [];
                  this.page = 0;
                  this.hasNext = true;
                  this.sort = 3;
                  getRecipeByCost();
                }
              "
              >비용 낮은순</v-list-item-title
            >
          </v-list-item>
          <v-list-item dense>
            <v-list-item-title
              class="carrot--text"
              v-on:click="
                () => {
                  this.sort = 'score';
                  this.sortString = '열량 낮은순';
                  this.recipeList = [];
                  this.page = 0;
                  this.hasNext = true;
                  this.sort = 1;
                  getRecipeByCalory();
                }
              "
              >열량 낮은순</v-list-item-title
            >
          </v-list-item>
          <v-list-item dense>
            <v-list-item-title
              class="carrot--text"
              v-on:click="
                () => {
                  this.sort = 'reviewDate,desc';
                  this.sortString = '평점순';
                  this.recipeList = [];
                  this.page = 0;
                  this.hasNext = true;
                  this.sort = 2;
                  getRecipeByScore();
                }
              "
              >평점순</v-list-item-title
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
    <organisms-recipe-card
      v-for="(recipe, i) in recipeList"
      :key="i"
      :recipe="recipe"
      class="mx-auto mt-10"
    >
    </organisms-recipe-card>
    <infinite-loading v-if="sort == 0" @infinite="getRecipeByIngredient">
      <div slot="no-more"></div>
    </infinite-loading>
    <infinite-loading v-else-if="sort == 1" @infinite="getRecipeByCalory">
      <div slot="no-more"></div>
    </infinite-loading>
    <infinite-loading v-else-if="sort == 3" @infinite="getRecipeByCost">
      <div slot="no-more"></div>
    </infinite-loading>
    <infinite-loading v-else @infinite="getRecipeByScore">
      <div slot="no-more"></div>
    </infinite-loading>
  </v-container>
</template>
<script>
import { mapState } from "vuex";
import axios from "axios";
import OrganismsRecipeCard from "../organisms/OrganismsRecipeCard.vue";
import InfiniteLoading from "vue-infinite-loading";
export default {
  name: "TemplatesRecipeList",
  created() {
    // this.getRecipeByIngredient();
  },
  data: function () {
    return {
      page: 0,
      size: 5,
      hasNext: true,
      recipeList: [],
      sortString: "재료일치 순",
      sort: 0,
    };
  },
  computed: {
    ...mapState({
      ingredientList(state) {
        var ingredient = [];
        var temp = state.ingredient.selectedIngredients;
        temp.forEach((e) => {
          ingredient.push({
            ingredientId: e.ingredientId,
            ingredientName: e.ingredientName,
          });
        });
        return ingredient;
      },
    }),
  },
  components: {
    OrganismsRecipeCard,
    InfiniteLoading,
  },
  methods: {
    getRecipeByIngredient($state) {
      var temp = this;
      axios
        .post("https://j7a604.p.ssafy.io/molaeng/search/ingredient", {
          ingredientList: temp.ingredientList,
          page: temp.page,
          size: temp.size,
        })
        .then((response) => {
          response.data.result.recipeList.forEach((e) => {
            temp.recipeList.push(e);
          });
          temp.page += 1;
          if (response.data.result.hasNext) {
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((error) => console.log(error));
    },
    getRecipeByCalory($state) {
      var temp = this;
      const url =
        "https://j7a604.p.ssafy.io/molaeng/search/calory?page=" +
        temp.page +
        "&size=" +
        temp.size +
        "&sort=recipeKcal&userId=1";
      axios
        .post(url, { ingredientList: temp.ingredientList })
        .then((response) => {
          response.data.result.recipeList.forEach((e) => {
            temp.recipeList.push(e);
          });
          temp.page += 1;
          if (response.data.result.hasNext) {
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((error) => console.log(error));
    },
    getRecipeByScore($state) {
      var temp = this;
      axios
        .post(
          "https://j7a604.p.ssafy.io/molaeng/search/score?userId=1&page=" +
            temp.page +
            "&size=5",
          {
            ingredientList: temp.ingredientList,
          }
        )
        .then((response) => {
          response.data.result.recipeList.forEach((e) => {
            temp.recipeList.push(e);
          });
          temp.page += 1;
          if (response.data.result.hasNext) {
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((error) => console.log(error));
    },
    getRecipeByCost($state) {
      var temp = this;
      axios
        .post(
          "https://j7a604.p.ssafy.io/molaeng/search/cost?userId=1&page=" +
            temp.page +
            "&size=5",
          {
            ingredientList: temp.ingredientList,
          }
        )
        .then((response) => {
          response.data.result.recipeList.forEach((e) => {
            temp.recipeList.push(e);
          });
          temp.page += 1;
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
<style lang=""></style>
