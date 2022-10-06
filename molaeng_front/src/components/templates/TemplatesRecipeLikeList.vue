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
    this.getUserIdByCookie();
  },
  async mounted() {
    this.RecipeLikeList = await api.getRecipeLikeList(this.userId);
  },
  methods: {
    getUserIdByCookie() {
      let userId = this.$cookies.get("userId");
      if (userId) {
        this.userId = parseInt(userId);
      }
    },
  },
};
</script>
