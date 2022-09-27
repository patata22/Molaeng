import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import SearchView from "@/views/SearchView.vue";
import SearchIngredient from "@/components/templates/TemplatesSearchIngredient.vue";
import SearchRecipe from "@/components/templates/TemplatesSearchRecipe.vue";
// import RecipeListView from "@/views/RecipeListView.vue";
import RecipeView from "@/views/RecipeView.vue";
// import MyPageView from "@/views/MyPageView.vue";
import MolaengDiaryView from "@/views/MolaengDiaryView.vue";
// import MenuView from "@/views/MenuView.vue";
// import InterestRecipeView from "@/views/InterestRecipeView.vue";
import OrganismRecipeReview from "../components/organisms/OrganismsRecipeReview";

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
    children: [
      {
        path: "/",
        redirect: "/ingredient",
      },
      {
        path: "/ingredient",
        component: SearchIngredient,
      },
      {
        path: "/recipe",
        component: SearchRecipe,
      },
    ],
  },
  // {
  //   path: "/recipeList",
  //   name: "recipeList",
  //   component: RecipeListView,
  // },
  {
    path: "/recipe",
    name: "recipe",
    component: RecipeView,
  },
  // {
  //   path: "/myPage",
  //   name: "myPage",
  //   component: MyPageView,
  // },
  {
    path: "/diary",
    name: "diary",
    component: MolaengDiaryView,
  },
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
  {
    path: "/review",
    name: "OrganismsRecipeReview",
    component: OrganismRecipeReview,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
