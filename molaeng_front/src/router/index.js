import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import SearchView from "@/views/SearchView.vue";
// import RecipeListView from "@/views/RecipeListView.vue";
// import RecipeDetailView from "@/views/RecipeDetailView.vue";
// import MyPageView from "@/views/MyPageView.vue";
// import MolaengDiaryView from "@/views/MolaengDiaryView.vue";
// import MenuView from "@/views/MenuView.vue";
// import InterestRecipeView from "@/views/InterestRecipeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/search",
    name: "search",
    component: SearchView,
  },
  // {
  //   path: "/recipeList",
  //   name: "recipeList",
  //   component: RecipeListView,
  // },
  // {
  //   path: "/recipe",
  //   name: "recipe",
  //   component: RecipeDetailView,
  // },
  // {
  //   path: "/myPage",
  //   name: "myPage",
  //   component: MyPageView,
  // },
  // {
  //   path: "/diary",
  //   name: "diary",
  //   component: MolaengDiaryView,
  // },
  // {
  //   path: "/recipeLike",
  //   name: "recipeLike",
  //   component: InterestRecipeView,
  // },
  // {
  //   path: "/menu",
  //   name: "menu",
  //   component: MenuView,
  // },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
