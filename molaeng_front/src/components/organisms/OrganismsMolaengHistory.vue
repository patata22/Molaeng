<template>
  <div>
    <div class="historyTopLine"></div>
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
.historyTopLine {
  border-style: solid none none;
  border-color: rgba(91, 87, 75, 10%);
  border-width: 15px;
}
.historyHeader {
  padding: 4%;
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
  box-shadow: 5px 5px 10px rgba(91, 87, 75, 10%);
}
</style>
