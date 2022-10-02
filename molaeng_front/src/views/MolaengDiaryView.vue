<template>
  <div>
    <molaeng-diary-header @click.native="dateNotSelected" />
    <molaeng-calendar
      :saveCost="saveCost"
      v-on:dateSelected="dateSelected"
      v-on:setDate="setDate"
      @click.native="dateNotSelected"
    />
    <molaeng-history
      :date="date"
      :isDateSelected="isDateSelected"
      v-if="isDateSelected"
    />
    <molaeng-graph v-on:setSaveCost="setSaveCost" v-else />
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
    saveCost: 0,
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
    setSaveCost(saveCost) {
      this.saveCost = saveCost;
    },
  },
};
</script>

<style></style>
