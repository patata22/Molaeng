<template>
  <div class="graphPage">
    <div class="selectGraphBtnGroup">
      <div @click="getWeekGraph" :style="selectWeekBtnSyle">주차별</div>
      <div @click="getMonthGraph" :style="selectMonthBtnSyle">월별</div>
    </div>
    <Bar
      :chart-options="weekChartOption"
      :chart-data="WeekChartData"
      v-if="selectWeekGraph"
    />
    <Bar
      :chart-options="MonthChartOption"
      :chart-data="MonthChartData"
      v-else
    />
  </div>
</template>

<script>
import API from "@/api/APIs";
const api = API;

import { Bar } from "vue-chartjs/legacy";
import {
  Chart as ChartJS,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(BarElement, CategoryScale, LinearScale);

export default {
  name: "BarChart",
  components: { Bar },
  data() {
    return {
      userId: 1,
      year: 2022,
      month: 10,
      res_month: {
        month: 0,
        year: 0,
        saveCostList: [],
      },
      res_week: {
        month: 0,
        year: 0,
        saveCostList: [],
      },
      selectWeekGraph: true,

      WeekChartData: {
        labels: ["1주차", "2주차", "3주차", "4주차", "5주차", "6주차"],
        datasets: [
          {
            backgroundColor: "",
            data: [],
          },
        ],
      },
      weekChartOption: {
        plugins: {
          legend: {
            display: false,
          },
        },
      },
      MonthChartData: {
        labels: [
          "이번달",
          "1개월전",
          "2개월전",
          "3개월전",
          "4개월전",
          "5개월전",
        ],
        datasets: [
          {
            backgroundColor: "",
            data: [],
          },
        ],
      },
      MonthChartOption: {
        plugins: {
          legend: {
            display: false,
          },
        },
      },
    };
  },
  async mounted() {
    this.res_week = await api.getWeekGraph(this.userId, this.year, this.month);
    this.WeekChartData.datasets[0].data = this.res_week.saveCostList;

    this.res_month = await api.getMonthGraph(
      this.userId,
      this.year,
      this.month
    );
    this.MonthChartData.datasets[0].data = this.res_month.saveCostList;

    this.chartColor();
  },
  computed: {
    selectWeekBtnSyle() {
      if (this.selectWeekGraph) {
        return "background-color: #fdac6d";
      } else {
        return "";
      }
    },
    selectMonthBtnSyle() {
      if (this.selectWeekGraph) {
        return "";
      } else {
        return "background-color: #fdac6d";
      }
    },
  },
  methods: {
    chartColor() {
      console.log(this.res_week.saveCostList.length);
      console.log(this.res_month.saveCostList.length);
      let colors = [];
      for (let i = 0; i < this.res_week.saveCostList.length; i++) {
        if (this.WeekChartData.datasets[0].data[i] < 0) {
          this.WeekChartData.datasets[0].data[i] =
            this.WeekChartData.datasets[0].data[i] * -1;
          colors.push("#ED8A53");
        } else {
          colors.push("#72A971");
        }
      }
      this.WeekChartData.datasets[0].backgroundColor = colors;

      for (let i = 0; i < this.res_month.saveCostList.length; i++) {
        if (this.MonthChartData.datasets[0].data[i] < 0) {
          this.MonthChartData.datasets[0].data[i] =
            this.MonthChartData.datasets[0].data[i] * -1;
          colors.push("#ED8A53");
        } else {
          colors.push("#72A971");
        }
      }
      this.MonthChartData.datasets[0].backgroundColor = colors;
    },
    getWeekGraph() {
      this.selectWeekGraph = true;
      console.log(this.selectWeekGraph);
    },
    getMonthGraph() {
      this.selectWeekGraph = false;
      console.log(this.selectWeekGraph);
    },
  },
};
</script>

<style>
.graphPage {
  margin: 4%;
}
.selectGraphBtnGroup {
  display: flex;
  width: 160px;
  margin-bottom: 2%;
  padding: 2px;
  float: right;
  background-color: rgba(91, 87, 75, 30%);
  border-radius: 28px;
  justify-content: space-between;
}
.selectGraphBtnGroup div {
  display: flex;
  width: 78px;
  height: 36px;
  justify-content: center;
  align-items: center;
  border-radius: 28px;
  color: white;
}
</style>
