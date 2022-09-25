<template>
  <div>
    <div>{{ review.reviewScore }}</div>
    <div>{{ review.userNickname }} / {{ review.reviewDate }}</div>
    <div>{{ review.reviewContent }}</div>
    <div>{{ review.likeCnt }}</div>
  </div>
</template>
<script>
export default {
  props: { review: { type: Object } },
};
</script>
<style></style>
