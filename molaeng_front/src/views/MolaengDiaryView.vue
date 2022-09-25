<template>
  <div>
    <div><molaeng-diary-header /></div>
    <div><molaeng-calendar /></div>
    <div><molaeng-graph /></div>
  </div>
</template>

<script>
import MolaengDiaryHeader from "../components/organisms/OrganismsMolaengDiaryHeader.vue";
import MolaengCalendar from "../components/organisms/OrganismsMolaengCalendar.vue";
import MolaengGraph from "../components/organisms/OrganismsMolaengGraph.vue";
export default {
  name: "MolaengDiaryView",
  components: {
    MolaengDiaryHeader,
    MolaengCalendar,
    MolaengGraph,
  },
  data: () => ({}),
};
</script>

<style></style>
