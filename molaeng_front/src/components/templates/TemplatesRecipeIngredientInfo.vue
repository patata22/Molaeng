<template>
  <table id="needIngredientList" class="mt-2 ma-auto">
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
export default {
  name: "RecipeIngredientInfo",
  data: () => ({
    ingredientList: [
      {
        ingredientId: 35,
        ingredientName: "당근",
        needWeight: 300.0,
        weightUnit: "g",
      },
      {
        ingredientId: 107,
        ingredientName: "양파",
        needWeight: 50,
        weightUnit: "g",
      },
    ],
  }),
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
