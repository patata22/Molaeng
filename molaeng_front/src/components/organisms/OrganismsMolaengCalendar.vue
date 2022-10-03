<template>
  <div class="calendarPage">
    <div class="calendarHeader">
      <span class="calendarHeaderDefault">{{ selectedGraph }} </span
      ><span class="calendarHeaderChange">{{ header }}</span>
    </div>
    <v-sheet class="calendarButton">
      <v-btn icon large @click="$refs.calendar.prev()">
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <v-spacer v-if="$refs.calendar">{{ $refs.calendar.title }}</v-spacer>
      <v-btn icon large @click="$refs.calendar.next()">
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
    </v-sheet>
    <v-sheet height="380">
      <v-calendar
        ref="calendar"
        class="calendar"
        v-model="value"
        :type="type"
        :weekdays="weekday"
        :weekday-format="getWeekDayFormat"
        :show-month-on-first="false"
        :events="events"
        :event-color="getEventColor"
        :event-text-color="getEventTextColor"
        :event-more="false"
        @click:date="dateSelected"
        @change="getPrices"
      ></v-calendar>
    </v-sheet>
  </div>
</template>

<script>
import API from "@/api/APIs";
const api = API;

export default {
  name: "MolaengCalendar",
  props: {
    selectedGraph: String,
    savedCost: Number,
  },
  data: () => ({
    userId: 1,
    year: 0,
    month: 0,
    header: "",
    res: {
      saveCostList: [{ mealDate: "", saveCost: 0 }],
    },
    date: "",
    value: "",
    type: "month",
    weekday: [0, 1, 2, 3, 4, 5, 6],
    weekDayFormat: ["S", "M", "T", "W", "T", "F", "S"],
    events: [],
    color: "none",
    textcolor: ["#72A971", "#ED8A53"],
  }),
  created() {
    this.changeHeader();
  },
  watch: {
    savedCost() {
      this.changeHeader();
    },
  },
  methods: {
    changeHeader() {
      if (this.savedCost > 0) {
        this.header = this.savedCost.toLocaleString() + "원 절약했어요!";
      } else {
        this.header = "절약한 금액이 없어요ㅠㅠ";
      }
    },
    async getPrices({ start }) {
      const events = [];

      this.year = start.date.split("-", 3)[0];
      this.month = start.date.split("-", 3)[1];

      this.res = await api.getCalendar(this.userId, this.year, this.month);

      for (let i = 0; i < this.res.saveCostList.length; i++) {
        let date = this.res.saveCostList[i].mealDate;
        let price = this.res.saveCostList[i].saveCost;
        if (price > 0) {
          price = price.toLocaleString();
          price = "+" + price;
        } else {
          price = price.toLocaleString();
        }

        events.push({
          name: price,
          start: date,
          color: this.color,
          textcolor: this.textcolor[this.setcolor(parseInt(price))],
        });
      }

      this.events = events;
    },
    getWeekDayFormat(date) {
      return this.weekDayFormat[date.weekday];
    },
    getEventColor(event) {
      return event.color;
    },
    getEventTextColor(event) {
      return event.textcolor;
    },
    dateSelected(value) {
      this.date = value.date;
      this.$emit("dateSelected");
      this.$emit("setDate", this.date);
    },
    setcolor(a) {
      if (a > 0) {
        return 0;
      } else {
        return 1;
      }
    },
  },
};
</script>

<style>
.calendarPage {
  margin: 2%;
}
.calendarHeader {
  padding-top: 5%;
  padding-bottom: 5%;
  text-align: center;
  font-size: 1rem;
  font-weight: bold;
}
.calendarHeaderDefault {
  color: #5b574b;
}
.calendarHeaderChange {
  color: #72a971;
}
.calendarButton {
  margin-left: 6%;
  margin-right: 6%;
  margin-bottom: 3%;
  display: flex;
  justify-content: center;
}
.spacer {
  align-self: center;
  text-align: center;
  font-size: 1.2rem;
  font-weight: 800;
  color: #5b574b;
}
.calendar {
  text-align: center;
}
.theme--light.v-calendar-weekly {
  border: 30px;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__head-weekday {
  padding-bottom: 1%;
  border: 0px;
  font-size: 1rem;
  font-weight: bold;
  color: #5b574b;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__head-weekday.v-outside {
  background-color: white;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__head-weekday.v-past {
  color: #5b574b;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__day {
  border: none;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__day.v-outside {
  visibility: hidden;
}
.v-calendar-weekly__day-label .v-btn {
  margin-top: 2%;
  font-size: 1rem;
}
.theme--light.v-btn.v-btn--has-bg {
  background-color: white;
}
.v-calendar .v-event {
  margin-top: -5%;
  margin-bottom: 2%;
  font-size: 0.76rem;
}
.v-application .pl-1 {
  padding: 0 !important;
}
</style>
