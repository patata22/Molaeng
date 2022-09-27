<template>
  <div>
    <div><molaeng-diary-header /></div>
    <div><molaeng-calendar /></div>
    <div><molaeng-history /></div>
    <div><molaeng-graph /></div>
  </div>
</template>

<script>
import MolaengDiaryHeader from "../components/organisms/OrganismsMolaengDiaryHeader.vue";
import MolaengCalendar from "../components/organisms/OrganismsMolaengCalendar.vue";
import MolaengHistory from "../components/organisms/OrganismsMolaengHistory.vue";
import MolaengGraph from "../components/organisms/OrganismsMolaengGraph.vue";
export default {
  name: "MolaengDiaryView",
  components: {
    MolaengDiaryHeader,
    MolaengCalendar,
    MolaengHistory,
    MolaengGraph,
  },
  data: () => ({}),
};
</script>

<style></style>
