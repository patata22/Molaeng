<template>
  <div class="graphPage">
    <div class="selectGraphBtnGroup">
      <button @click="getWeekGraph">주차별</button>
      <button @click="getMonthGraph">월별</button>
    </div>
    <Bar :chart-options="chartOptions" :chart-data="chartData" />
  </div>
</template>

<script>
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
      chartData: {
        labels: ["1주차", "2주차", "3주차", "4주차", "5주차", "6주차"],
        datasets: [
          {
            backgroundColor: "#ED8A53",
            data: [400, 2000, -5000, -3000, 7000, -500],
          },
        ],
      },
      chartOptions: {
        plugins: {
          legend: {
            display: false,
          },
        },
      },
    };
  },
  mounted() {
    this.chartColor();
  },
  methods: {
    chartColor() {
      let size = this.chartData.datasets[0].data.length;
      let colors = [];
      for (let i = 0; i < size; i++) {
        if (this.chartData.datasets[0].data[i] < 0) {
          this.chartData.datasets[0].data[i] =
            this.chartData.datasets[0].data[i] * -1;
          colors.push("#ED8A53");
        } else {
          colors.push("#72A971");
        }
      }
      this.chartData.datasets[0].backgroundColor = colors;
    },
    getWeekGraph() {
      console.log("주차별");
    },
    getMonthGraph() {
      console.log("월별");
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
.selectGraphBtnGroup button {
  width: 78px;
  height: 36px;
  border-radius: 28px;
  color: white;
}
.selectGraphBtnGroup button:focus {
  background-color: #fdac6d;
}
</style>
