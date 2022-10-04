<template>
  <table id="needIngredientList" class="mt-4 mb-8">
    <colgroup>
      <col style="width: 50%" />
      <col style="width: 50%" />
    </colgroup>
    <thead>
      <tr>
        <th class="text-left dark--text font-weight-bold">주재료명</th>
        <th class="text-left dark--text font-weight-bold text-right">용량</th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="ingredient in recipeIngredientList"
        :key="ingredient.ingredientId"
      >
        <td class="font-weight-bold dark--text">
          {{ ingredient.ingredientName }}
        </td>
        <td class="dark--text text-right">
          {{ ingredient.needWeight }}{{ ingredient.weightUnit }}
        </td>
      </tr>
    </tbody>
    <br />
    <thead>
      <tr>
        <th class="text-left dark--text font-weight-bold">부재료명</th>
        <th class="text-left dark--text font-weight-bold text-right">용량</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(ingredient, index) in subIngredientList" :key="index">
        <td class="font-weight-bold dark--text">
          {{ ingredient.subIngredientName }}
        </td>
        <td class="dark--text text-right">
          {{ ingredient.weightUnit }}
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
    recipeIngredientList: Array,
  },
  data: () => ({
    subIngredientList: [],
  }),
  async created() {
    let result = await api.getRecipeSubIngredients(this.recipeId);
    this.subIngredientList = result.subIngredientList;
  },
};
</script>

<style>
#needIngredientList {
  border-collapse: collapse;
  margin-top: 10px;
  width: 90%;
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
