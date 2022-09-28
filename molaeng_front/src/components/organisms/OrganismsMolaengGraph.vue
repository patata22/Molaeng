<template>
  <Bar :chart-data="chartData" />
</template>

<script>
import { Bar } from "vue-chartjs/legacy";
import {
  Chart as ChartJS,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: "BarChart",
  components: { Bar },
  data() {
    return {
      chartData: {
        labels: ["1주차", "2주차", "3주차", "4주차", "5주차"],
        datasets: [
          {
            backgroundColor: "#ED8A53",
            data: [4000, 2000, -5000, -3000, 7000],
          },
        ],
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
          colors.push("#72A971");
        } else {
          colors.push("#ED8A53");
        }
      }
      this.chartData.datasets[0].backgroundColor = colors;
    },
  },
};
</script>
