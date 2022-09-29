<template>
  <div>
    <nav-bar />
    <molaeng-diary-header @click.native="dateNotSelected" />
    <molaeng-calendar
      v-on:dateSelected="dateSelected"
      @click.native="dateNotSelected"
    />
    <molaeng-history v-if="isDateSelected" />
    <molaeng-graph v-else />
  </div>
</template>

<script>
import NavBar from "../components/molecules/MoleculesNavBar.vue";
import MolaengDiaryHeader from "../components/organisms/OrganismsMolaengDiaryHeader.vue";
import MolaengCalendar from "../components/organisms/OrganismsMolaengCalendar.vue";
import MolaengHistory from "../components/organisms/OrganismsMolaengHistory.vue";
import MolaengGraph from "../components/organisms/OrganismsMolaengGraph.vue";
export default {
  name: "MolaengDiaryView",
  components: {
    NavBar,
    MolaengDiaryHeader,
    MolaengCalendar,
    MolaengHistory,
    MolaengGraph,
  },
  data: () => ({
    isDateSelected: false,
  }),
  methods: {
    dateSelected() {
      this.isDateSelected = true;
    },
    dateNotSelected() {
      this.isDateSelected = false;
    },
  },
};
</script>

<style></style>
