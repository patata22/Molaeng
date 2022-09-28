<template>
  <div class="historyPage">
    <div class="historyHeader">내가 먹은 메뉴</div>
    <div class="historyList">
      <div class="historyItem">양파스프</div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.historyPage {
  padding: 4%;
  border-style: solid none solid;
  border-color: rgba(91, 87, 75, 10%);
  border-width: 10px 0px 1px;
}
.historyHeader {
  font-size: 1.2rem;
  font-weight: 900;
  color: #5b574b;
}
.historyList {
  min-height: 200px;
  text-align: center;
}
.historyItem {
  width: 80%;
  height: 40%;
  border-radius: 15px;
  box-shadow: 0px 0px 10px rgb(91 87 75 / 10%);
}
</style>
