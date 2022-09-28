<template>
  <div class="historyPage">
    <div class="historyHeader">내가 먹은 메뉴</div>
    <div class="historyList">
      <molaeng-history-item /><molaeng-history-item /><molaeng-history-item />
    </div>
  </div>
</template>

<script>
import MolaengHistoryItem from "../molecules/MoleculesMolaengHistoryItem.vue";
export default {
  components: {
    MolaengHistoryItem,
  },
};
</script>

<style>
.historyPage {
  padding: 4% 6%;
  border-style: solid none solid;
  border-color: rgba(91, 87, 75, 10%);
  border-width: 12px 0px 1px;
}
.historyHeader {
  font-size: 1.2rem;
  font-weight: 900;
  color: #5b574b;
}
.historyList {
  min-height: 200px;
}
</style>
