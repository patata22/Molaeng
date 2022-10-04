<template>
  <div>
    <molaeng-diary-header
      class="mx-auto"
      style="max-width: 420px"
      @click.native="dateNotSelected"
    />
    <molaeng-calendar
      class="mx-auto"
      style="max-width: 420px"
      :selectedGraph="selectedGraph"
      :savedCost="savedCost"
      v-on:dateSelected="dateSelected"
      v-on:setDate="setDate"
      @click.native="dateNotSelected"
    />
    <molaeng-history
      class="mx-auto"
      style="max-width: 420px"
      :date="date"
      :isDateSelected="isDateSelected"
      v-if="isDateSelected"
    />
    <molaeng-graph
      class="mx-auto"
      style="max-width: 420px"
      v-on:setSelectedGraph="setSelectedGraph"
      v-on:setSavedCost="setSavedCost"
      v-else
    />
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
  data: () => ({
    isDateSelected: false,
    date: "",
    selectedGraph: "",
    savedCost: 0,
  }),
  methods: {
    dateSelected() {
      this.isDateSelected = true;
    },
    dateNotSelected() {
      this.isDateSelected = false;
    },
    setDate(date) {
      this.date = date;
    },
    setSavedCost(savedCost) {
      this.savedCost = savedCost;
    },
    setSelectedGraph(selectedGraph) {
      this.selectedGraph = selectedGraph;
    },
  },
};
</script>

<style></style>
