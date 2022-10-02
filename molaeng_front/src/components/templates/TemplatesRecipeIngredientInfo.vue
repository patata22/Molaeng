<template>
  <table id="needIngredientList" class="mt-4 mb-8 mx-auto">
    <colgroup>
      <col style="width: 50%" />
      <col style="width: 50%" />
    </colgroup>
    <thead>
      <tr>
        <th class="text-left dark--text font-weight-bold">재료명</th>
        <th class="text-left dark--text font-weight-bold">용량</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="ingredient in ingredientList" :key="ingredient.ingredientId">
        <td class="font-weight-bold dark--text">
          {{ ingredient.ingredientName }}
        </td>
        <td class="dark--text">
          {{ ingredient.needWeight }}{{ ingredient.weightUnit }}
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import API from "@/api/APIs";

const api = API;

export default {
  name: "RecipeIngredientInfo",
  props: {
    recipeId: String,
  },
  data: () => ({
    ingredientList: [],
  }),
  async mounted() {
    let result = await api.getRecipeIngredients(this.recipeId);
    this.ingredientList = result.ingredientList;
  },
};
</script>

<style>
#needIngredientList {
  border-collapse: collapse;
  margin-top: 10px;
}

#needIngredientList th {
  padding: 10px;
  border-bottom: 1.5px solid #5b574b;
}
#needIngredientList td {
  padding: 10px;
  border-bottom: 1px solid #cecdc9;
}
</style>
