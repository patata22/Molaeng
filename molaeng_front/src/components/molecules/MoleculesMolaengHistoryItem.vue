<template>
  <div class="historyItem">
    <span>양파스프</span>
    <div class="historyItemRight">
      <span class="historyItemPrice">-2,300원</span>
      <button @click="deleteHistory">
        <v-icon class="deleteHistoryBtn">mdi-window-close</v-icon>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({}),
  methods: {
    deleteHistory() {
      console.log("삭제합니다");
    },
  },
};
</script>

<style>
.historyItem {
  display: flex;
  width: 90%;
  height: 70px;
  margin: 0 auto;
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
  font-weight: bold;
}
.deleteHistoryBtn:active {
  color: #5b574b;
}
</style>
