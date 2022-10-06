<template>
  <div align="center">
    <recipe-card
      v-for="(recipe, i) in RecipeLikeList"
      :key="i"
      :recipe="recipe"
      class="mt-10"
    >
    </recipe-card>
  </div>
</template>

<script>
import RecipeCard from "@/components/organisms/OrganismsRecipeCardWithoutSelect.vue";
import API from "@/api/APIs";
const api = API;

export default {
  name: "RecipeLikeList",
  components: {
    RecipeCard,
  },
  data() {
    return {
      RecipeLikeList: {},
      userId: 1,
    };
  },
  created() {
    console.log("created 실행!!");
    this.getUserIdByCookie();
  },
  async mounted() {
    console.log("mounted 실행!!");
    this.RecipeLikeList = await api.getRecipeLikeList(this.userId);
    console.log(this.RecipeLikeList);
  },
  methods: {
    getUserIdByCookie() {
      let userId = this.$cookies.get("userId");
      console.log(userId);
      if (userId) {
        this.userId = parseInt(userId);
      }
    },
  },
};
</script>
