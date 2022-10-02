<template>
  <div class="historyPage">
    <div class="historyHeader">내가 먹은 레시피</div>
    <div class="historyList">
      <molaeng-history-item
        v-for="(meal, i) in mealList"
        :key="i"
        :meal="meal"
      />
    </div>
  </div>
</template>

<script>
import API from "@/api/APIs";
const api = API;

import MolaengHistoryItem from "../molecules/MoleculesMolaengHistoryItem.vue";

export default {
  components: {
    MolaengHistoryItem,
  },
  props: {
    date: String,
    isDateSelected: Boolean,
  },
  data: () => ({
    userId: 1,
    mealList: [
      {
        diaryId: 0,
        recipeId: 0,
        recipeName: "",
        saveCost: 0,
      },
    ],
  }),
  created() {
    this.getMealList();
  },
  watch: {
    date() {
      this.getMealList();
    },
  },
  methods: {
    async getMealList() {
      this.res = await api.getDiary(this.userId, this.date);
      this.mealList = this.res.mealList;
    },
  },
};
</script>

<style>
.historyPage {
  margin-top: 4%;
  padding: 4% 6%;
  border-style: solid none solid;
  border-color: rgba(91, 87, 75, 10%);
  border-width: 12px 0px 1px;
}
.historyHeader {
  font-size: 1.2rem;
  font-weight: 800;
  color: #5b574b;
}
.historyList {
  min-height: 200px;
}
</style>
