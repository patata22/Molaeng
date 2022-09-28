<template lang="">
  <v-card>
    <v-card-text>
      <AtomsReviewScoreStar class="d-inline"></AtomsReviewScoreStar>
      <AtomsProgressBar :ratio="ratioFive"></AtomsProgressBar>
      <div class="d-inline">{{ scoreCnt[4] }}</div>
    </v-card-text>
    <v-card-text>
      <AtomsReviewScoreStar d-inline></AtomsReviewScoreStar>
      <AtomsProgressBar :ratio="ratioFour" d-inline></AtomsProgressBar>
      <div d-inline>{{ scoreCnt[3] }}</div>
    </v-card-text>
    <v-card-text>
      <AtomsReviewScoreStar d-inline></AtomsReviewScoreStar>
      <AtomsProgressBar :ratio="ratioThree" d-inline></AtomsProgressBar>
      <div d-inline>{{ scoreCnt[2] }}</div>
    </v-card-text>
    <v-card-text>
      <AtomsReviewScoreStar d-inline></AtomsReviewScoreStar>
      <AtomsProgressBar :ratio="ratioTwo" d-inline></AtomsProgressBar>
      <div d-inline>{{ scoreCnt[1] }}</div>
    </v-card-text>
    <v-card-text>
      <AtomsReviewScoreStar d-inline></AtomsReviewScoreStar>
      <AtomsProgressBar :ratio="ratioOne" d-inline></AtomsProgressBar>
      <div d-inline>{{ scoreCnt[0] }}</div>
    </v-card-text>
  </v-card>
</template>
<script>
import AtomsReviewScoreStar from "@/components/atoms/AtomsReviewScoreStar";
import AtomsProgressBar from "@/components/atoms/AtomsProgressBar";
export default {
  name: "MoleculesRecipeScore2",
  data: function () {
    return {
      scoreCnt: [1, 2, 3, 4, 5],
    };
  },
  // props: {
  //   scoreCnt: Object,
  // },
  computed: {
    ratioFive() {
      return (
        this.scoreCnt[4] /
        (this.scoreCnt[0] +
          this.scoreCnt[1] +
          this.scoreCnt[2] +
          this.scoreCnt[3] +
          this.scoreCnt[4])
      );
    },
    ratioFour() {
      return (
        this.scoreCnt[3] /
        (this.scoreCnt[0] +
          this.scoreCnt[1] +
          this.scoreCnt[2] +
          this.scoreCnt[3] +
          this.scoreCnt[4])
      );
    },
    ratioThree() {
      return (
        this.scoreCnt[2] /
        (this.scoreCnt[0] +
          this.scoreCnt[1] +
          this.scoreCnt[2] +
          this.scoreCnt[3] +
          this.scoreCnt[4])
      );
    },
    ratioTwo() {
      return (
        this.scoreCnt[1] /
        (this.scoreCnt[0] +
          this.scoreCnt[1] +
          this.scoreCnt[2] +
          this.scoreCnt[3] +
          this.scoreCnt[4])
      );
    },
    ratioOne() {
      return (
        this.scoreCnt[0] /
        (this.scoreCnt[0] +
          this.scoreCnt[1] +
          this.scoreCnt[2] +
          this.scoreCnt[3] +
          this.scoreCnt[4])
      );
    },
  },
  components: {
    AtomsProgressBar,
    AtomsReviewScoreStar,
  },
};
</script>
<style lang=""></style>
