<template>
  <Bar
    class="mt-2"
    :chart-data="chartData"
    :chart-options="chartOptions"
    v-if="isSell"
  />
  <div
    v-else
    class="pa-5 font-weight-bold"
    style="font-size: 15px; color: #5b574b"
  >
    <v-icon class="mb-1" style="font-size: 80px; color: #5b574b"
      >mdi-emoticon-cry-outline</v-icon
    ><br />
    이 메뉴를 파는 식당이<br />
    등록되지 않았어요ㅠㅠ
  </div>
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
// import ChartJsPluginDataLabels from "chartjs-plugin-datalabels";

ChartJS.register(
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
  // ChartJsPluginDataLabels
);

export default {
  name: "OutPriceGraph",
  components: { Bar },
  data() {
    return {
      isSell: false,
      chartData: {
        labels: [],
        datasets: [
          {
            backgroundColor: [],
            data: [],
            barPercentage: 0.5,
            datalabels: {
              color: "#000000",
              anchor: "end",
              align: "end",
              offset: "-5",
              formatter: function (value, context) {
                let idx = context.dataIndex;
                return context.dataset.data[idx].toLocaleString();
              },
            },
          },
        ],
      },
      chartOptions: {
        layout: {
          padding: 12,
        },
        scales: {
          y: {
            grid: {
              display: false,
            },
            ticks: {
              display: false,
            },
          },
          x: {
            grid: {
              display: false,
            },
          },
        },
        plugins: {
          legend: {
            display: false,
          },
        },
      },
    };
  },
  props: {
    seoul: Number,
    my: Number,
    gugunName: String,
    recipePrice: Number,
  },
  mounted() {
    this.graphSeoul();
    this.graphMy();
  },
  watch: {
    seoul() {
      this.graphSeoul();
    },
    my() {
      this.graphMy();
    },
  },
  methods: {
    graphSeoul() {
      if (this.seoul > 0) {
        this.isSell = true;
        this.chartData.labels.push("서울 평균");
        this.chartData.datasets[0].data.push(this.seoul);
        this.chartData.datasets[0].backgroundColor.push("#ED8A53");
        this.chartData.labels.push("조리 가격");
        this.chartData.datasets[0].data.push(this.recipePrice);
        this.chartData.datasets[0].backgroundColor.push("#72A971");
      } else {
        this.isSell = false;
      }
    },
    graphMy() {
      if (this.my > 0) {
        this.chartData.labels.push(this.gugunName);
        this.chartData.datasets[0].data.push(this.my);
        this.chartData.datasets[0].backgroundColor.push("#ED8A53");
      }
    },
  },
};
</script>
