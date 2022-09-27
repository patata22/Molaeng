<template>
  <div id="back">
    <div class="d-flex flex-row justify-space-between">
      <div class="column">aa</div>
      <div class="column">bb</div>
    </div>
    <div class="d-flex flex-row justify-space-between">
      <div class="column">cc</div>
      <div class="column">dd</div>
    </div>
    <div class="column-bottom">ee</div>
  </div>
</template>

<script>
export default {};
</script>

<style>
#back {
  background-color: white;
  width: 90%;
  height: calc(100vh - 445px);
  border-radius: 20px;
  margin: 0 auto;
}
#back .column {
  text-align: center;
  width: 90%;
  height: calc((100vh - 445px) / 3);
  background-color: #5b574b;
  opacity: 15%;
  border-radius: 20px;
  margin: 0 auto;
}
#back .column-bottom {
  text-align: center;
  width: 90%;
  height: calc((100vh - 445px) / 3);
  background-color: #5b574b;
  opacity: 15%;
  border-radius: 20px;
  margin: 0 auto;
}
</style>
