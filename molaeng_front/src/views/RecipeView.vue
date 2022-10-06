<template>
  <div>
    <recipe-detail-header-image
      class="mx-auto"
      style="max-width: 420px"
      :recipeInfo="recipeInfo"
    />
    <div
      class="mx-auto"
      style="
        position: sticky;
        top: 0;
        z-index: 3;
        background-color: white;
        max-width: 420px;
      "
    >
      <recipe-detail-header
        v-bind:recipeInfo="recipeInfo"
        :userId="userId"
        :recipeId="recipeId"
        :recipeIngredientList="recipeIngredientList"
      />
      <menu-tab :tabs="tabs" />
    </div>
    <router-view
      class="mx-auto"
      :userId="userId"
      :recipeId="recipeId"
      :outeat="outeat"
      :recipePrice="recipePrice"
      :totalPrice="totalPrice"
      :ingredientList="ingredientList"
      :recipeIngredientList="recipeIngredientList"
    ></router-view>
  </div>
</template>

<script>
import RecipeDetailHeaderImage from "../components/atoms/AtomsRecipeDetailHeaderImage.vue";
import RecipeDetailHeader from "../components/organisms/OrganismsRecipeDetailHeader.vue";
import MenuTab from "../components/molecules/MoleculesMenuTab.vue";
import { mapGetters } from "vuex";

import API from "@/api/APIs";
const api = API;

// recipeList에서 레시피아이디를 받아서 axios 요청 보내는 코드 추가해야 함
// 레시피아이디를 어떻게 받아오는지..?
export default {
  name: "RecipeView",
  components: {
    RecipeDetailHeaderImage,
    RecipeDetailHeader,
    MenuTab,
  },
  data: () => ({
    userId: 0,
    recipeId: "",
    //레시피 상단바 상세정보
    recipeInfo: {},
    recipeIngredientList: [],
    outeat: {
      seoul: 0,
      my: 0,
      gugunName: "",
    },
    totalPrice: 0,
    ingredientList: [],
  }),
  computed: {
    ...mapGetters(["selectedIngredients", "selectedIngredientIds"]),
    tabs() {
      return [
        {
          tabName: "재료",
          tabLink: "/recipe/" + this.recipeId + "/inginfo",
        },
        {
          tabName: "조리방법",
          tabLink: "/recipe/" + this.recipeId + "/description",
        },
        {
          tabName: "가격정보",
          tabLink: "/recipe/" + this.recipeId + "/price",
        },
        {
          tabName: "리뷰",
          tabLink: "/recipe/" + this.recipeId + "/review",
        },
      ];
    },
    compareLocation() {
      if (this.outeat.my > 0) {
        return this.outeat.gugunName;
      } else return "서울";
    },
    comparePrice() {
      if (this.compareLocation == "서울") {
        return this.outeat.seoul - this.recipePrice;
      } else return this.outeat.my - this.recipePrice;
    },
    recipePrice() {
      let recipePrice = 0;
      for (let i = 0; i < this.ingredientList.length; i++) {
        let ingredient = this.ingredientList[i];
        if (!this.selectedIngredientIds.includes(ingredient.ingredientId)) {
          recipePrice += Number(ingredient.price);
        }
      }
      return recipePrice;
    },
  },
  async created() {
    this.getUserIdByCookie();
    //recipeId 저장
    const pathName = this.$route.fullPath.split("/");
    this.recipeId = pathName[2];
    //레시피 상단바 상세정보
    this.recipeInfo = await api.getRecipeInfo(this.recipeId, this.userId);
    //레시피 주재료 리스트

    //설명 : ingredientId, ingredientName, selected를 가진 객체 배열 recipeIngredientList를 만들고
    //axios로 가져온 주재료 리스트와 store의 selectedIngredients를 비교해서 있으면 selected를 true, 없으면 false로 만들고
    //recipeIngredientList에 넣어서 상단바로 보내기
    this.getRecipeIngredients();
    this.getHomePrice();

    this.outeat = await api.outPrice(this.recipeId, this.userId);

    this.addLocalStorage();
  },
  methods: {
    addLocalStorage() {
      let newList = [];
      let recentList = JSON.parse(localStorage.getItem("recentRecipe"));
      if (recentList) {
        for (let item of recentList) {
          if (!(item == this.recipeId)) {
            newList.push(item);
          }
        }
      }
      newList.unshift(this.recipeId);
      if (newList.length > 5) newList.pop();
      localStorage.setItem("recentRecipe", JSON.stringify(newList));
    },
    getUserIdByCookie() {
      let userId = this.$cookies.get("userId");
      if (userId) {
        this.userId = parseInt(userId);
      }
    },

    /**
     * 설명 : ingredientId, ingredientName, selected를 가진 객체 배열 recipeIngredientList를 만들고
     * axios로 가져온 주재료 리스트와 store의 selectedIngredients를 비교해서 있으면 selected를 true, 없으면 false로 만들고
     * recipeIngredientList에 넣어서 상단바로 보내기
     * tmp에 바로 selected 추가 가능
     */
    async getRecipeIngredients() {
      let result = await api.getRecipeIngredients(this.recipeId);
      let tmp = result.ingredientList;
      for (let i = 0; i < tmp.length; i++) {
        tmp[i].selected = false;
        for (let j = 0; j < this.selectedIngredients.length; j++) {
          if (tmp[i].ingredientId == this.selectedIngredients[j].ingredientId) {
            tmp[i].selected = true;
            break;
          }
        }
        this.recipeIngredientList.push(tmp[i]);
      }
    },
    async getHomePrice() {
      let result = await api.getHomePrice(this.recipeId);
      this.ingredientList = result.ingredientList;
      for (let i = 0; i < this.ingredientList.length; i++) {
        let ingredient = this.ingredientList[i];
        this.totalPrice += Number(ingredient.price);
      }
    },
  },
};
</script>
<style></style>
