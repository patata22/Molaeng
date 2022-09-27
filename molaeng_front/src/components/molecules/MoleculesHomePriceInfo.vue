<template>
  <div class="py-1">
    <table id="ingredientPriceList" class="mx-auto">
      <colgroup>
        <col style="width: 50%" />
        <col style="width: 50%" />
      </colgroup>
      <tbody>
        <tr v-for="ingredient in ingredientList" :key="ingredient.ingredientId">
          <td class="font-weight-bold dark--text px-1 py-1">
            {{ ingredient.ingredientName }}
          </td>
          <td class="dark--text text-right px-1 py-1">
            {{ ingredient.ingredientPrice }}원
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "HomePriceInfo",
  data: () => ({
    ingredientList: [
      {
        ingredientId: 35,
        ingredientName: "당근",
        ingredientPrice: 3000,
      },
      {
        ingredientId: 107,
        ingredientName: "양파",
        ingredientPrice: 2000,
      },
      {
        ingredientId: 36,
        ingredientName: "가지",
        needWeight: 900,
      },
    ],
  }),
};
</script>

<style>
#ingredientPriceList {
  width: 90%;
  border-collapse: collapse;
}
#ingredientPriceList table {
  width: 90%;
}
</style>
