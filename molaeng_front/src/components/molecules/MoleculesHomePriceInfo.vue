<template>
  <div class="py-1">
    <table id="ingredientPriceList" class="mx-auto">
      <colgroup>
        <col style="width: 50%" />
        <col style="width: 50%" />
      </colgroup>
      <tbody>
        <tr v-for="ingredient in ingredientList" :key="ingredient.ingredientId">
          <td
            class="font-weight-bold dark--text px-1 py-1"
            :class="{
              'semidark--text': selectedIngredientIds.includes(
                ingredient.ingredientId
              ),
            }"
          >
            {{ ingredient.ingredientName }}
          </td>
          <td
            class="dark--text text-right px-1 py-1"
            :class="{
              'semidark--text': selectedIngredientIds.includes(
                ingredient.ingredientId
              ),
            }"
          >
            {{ ingredient.price }}원
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "HomePriceInfo",
  props: {
    ingredientList: Array,
  },
  computed: {
    ...mapGetters(["selectedIngredientIds"]),
  },
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
