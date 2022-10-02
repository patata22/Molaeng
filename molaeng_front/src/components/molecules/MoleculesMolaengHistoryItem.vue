<template>
  <div class="historyItem">
    <span @click="moveToRecipe">{{ meal.recipeName }}</span>
    <div class="historyItemRight">
      <span class="historyItemPrice">{{ meal.saveCost }}원</span>
      <button @click="deleteHistory">
        <v-icon class="deleteHistoryBtn">mdi-window-close</v-icon>
      </button>
    </div>
  </div>
</template>

<script>
import API from "@/api/APIs";
const api = API;

export default {
  props: { meal: Object },
  data: () => ({}),
  methods: {
    async deleteHistory() {
      this.res = await api.deleteDiary(this.meal.diaryId);
      this.$router.go();
    },
    moveToRecipe() {
      this.$router.push("/recipe/" + this.meal.recipeId);
    },
  },
};
</script>

<style>
.historyItem {
  display: flex;
  width: 90%;
  height: 70px;
  margin: auto;
  margin-top: 4%;
  padding-left: 4%;
  padding-right: 4%;
  justify-content: space-between;
  line-height: 70px;
  border-radius: 15px;
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
  font-size: 1.1rem;
  font-weight: bold;
  color: #5b574b;
}
.historyItemRight {
  display: flex;
  justify-content: space-between;
}
.historyItemPrice {
  margin-right: 10px;
  color: #ed8a53;
}
.deleteHistoryBtn {
  color: rgba(91, 87, 75, 50%);
}
.deleteHistoryBtn:active {
  color: #5b574b;
}
</style>
