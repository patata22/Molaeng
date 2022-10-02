<template>
  <div class="historyItem">
    <span class="historyItemLeft" @click="moveToRecipe">{{
      meal.recipeName
    }}</span>
    <div>
      <span class="historyItemPrice">{{ meal.saveCost }}원</span>
      <button @click.stop="dialog = true">
        <v-icon class="deleteHistoryBtn">mdi-window-close</v-icon>
      </button>
    </div>
    <v-dialog v-model="dialog">
      <v-card class="historyDeleteDialog">
        <v-card-title class="dialogtitle">모랭일기 삭제하기</v-card-title>
        <v-card-text>이 레시피를 모랭일기에서 삭제하시겠어요?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn depressed color="primary" white--text @click="deleteHistory"
            >예</v-btn
          >
          <v-btn outlined color="primary" @click="dialog = false">아니요</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import API from "@/api/APIs";
const api = API;

export default {
  props: { meal: Object },
  data: () => ({
    dialog: false,
  }),
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
.v-dialog {
  box-shadow: none;
}
.v-card__title {
  font-weight: bold !important;
  color: #5b574b;
}
.v-card__text {
  font-weight: bold !important;
  color: rgba(91, 87, 75, 50%) !important;
}
.historyDialog {
  margin: 0 auto;
}
.historyItem {
  display: flex;
  width: 92%;
  min-height: 60px;
  margin: auto;
  margin-top: 4%;
  padding: 4% 3% 4% 4%;
  justify-content: space-between;
  align-items: center;
  border-radius: 15px;
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
  font-weight: bold;
  color: #5b574b;
}
.historyItemLeft {
  font-size: 1.1rem;
  width: 60%;
}
.historyItemPrice {
  margin-right: 4px;
  font-size: 1rem;
  color: #ed8a53;
}
.deleteHistoryBtn {
  color: rgba(91, 87, 75, 50%);
}
.deleteHistoryBtn:active {
  color: #5b574b;
}
</style>
