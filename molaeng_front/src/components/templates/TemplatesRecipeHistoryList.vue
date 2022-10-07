<template>
  <div align="center">
    <div class="semidark--text my-4">최근 본 레시피는 5개까지 저장돼요</div>
    <recipe-card
      v-for="(recipe, i) in recipeList"
      :key="i"
      :recipe="recipe"
      class="mb-10"
    >
    </recipe-card>
  </div>
</template>

<script>
import RecipeCard from "@/components/organisms/OrganismsRecipeCardWithoutSelect.vue";
import API from "@/api/APIs";
const api = API;
export default {
  name: "RecipeHistoryList",
  components: {
    RecipeCard,
  },
  data() {
    return {
      recentRecipeList: [],
      recipeList: [],
    };
  },
  created() {
    this.recentRecipeList = JSON.parse(localStorage.recentRecipe);
  },
  async mounted() {
    this.recipeList = await api.recentRecipe(
      this.recentRecipeList,
      this.$cookies.get("userId")
    );
  },
};
</script>

<style></style>
