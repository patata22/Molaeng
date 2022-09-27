import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "@/views/MainView.vue";
import SearchView from "@/views/SearchView.vue";
import SearchIngredient from "@/components/templates/TemplatesSearchIngredient.vue";
import SearchRecipe from "@/components/templates/TemplatesSearchRecipe.vue";
import RecipeListView from "@/views/RecipeListView.vue";
import RecipeView from "@/views/RecipeView.vue";
import MyPageView from "@/views/MyPageView.vue";
import MolaengDiaryView from "@/views/MolaengDiaryView.vue";
import MenuView from "@/views/MenuView.vue";
import InterestRecipeView from "@/views/InterestRecipeView.vue";
import OrganismRecipeReview from "../components/organisms/OrganismsRecipeReview";
import RecipeHistory from "@/components/templates/TemplatesRecipeHistoryList.vue";
import RecipeLike from "@/components/templates/TemplatesRecipeLikeList.vue";
import EditProfile from "@/components/templates/TemplatesEditProfile.vue";
import MyReview from "@/components/templates/TemplatesMyReviewList.vue";
import MemberMenu from "@/components/templates/TemplatesMemberMenu.vue";
import UserMenu from "@/components/templates/TemplatesUserMenu.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/search",
    name: "search",
    component: SearchView,
    children: [
      // {
      //   path: "",
      //   redirect: "/ingredient",
      // },
      {
        path: "ingredient",
        alias: [""],
        component: SearchIngredient,
      },
      {
        path: "recipe",
        component: SearchRecipe,
      },
    ],
  },
  {
    path: "/recipeList",
    name: "recipeList",
    component: RecipeListView,
  },
  {
    path: "/recipe",
    name: "recipe",
    component: RecipeView,
  },
  {
    path: "/myPage",
    name: "myPage",
    component: MyPageView,
    children: [
      {
        path: "editProfile",
        alias: [""],
        component: EditProfile,
      },
      {
        path: "myReview",
        component: MyReview,
      },
    ],
  },
  {
    path: "/diary",
    name: "diary",
    component: MolaengDiaryView,
  },
  {
    path: "/interestRecipe",
    name: "interestRecipe",
    component: InterestRecipeView,
    children: [
      {
        path: "recipeLike",
        alias: [""],
        component: RecipeLike,
      },
      {
        path: "recipeHistory",
        component: RecipeHistory,
      },
    ],
  },
  {
    path: "/menu",
    name: "menu",
    component: MenuView,
    children: [
      {
        path: "member",
        alias: [""],
        component: MemberMenu,
      },
      {
        path: "user",
        component: UserMenu,
      },
    ],
  },
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
