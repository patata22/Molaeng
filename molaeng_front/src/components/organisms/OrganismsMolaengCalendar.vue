<template>
  <div class="calendarPage">
    <div class="calendarHeader">
      <span class="calendarHeaderDefault">이번 주는 </span
      ><span class="calendarHeaderChange">2,300원 절약했어요!</span>
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
    <v-sheet height="400">
      <v-calendar
        ref="calendar"
        class="calendar"
        v-model="value"
        :weekdays="weekday"
        :show-month-on-first="false"
        :weekday-format="getWeekDayFormat"
        :type="type"
        :events="events"
        :event-overlap-mode="mode"
        :event-overlap-threshold="30"
        :event-color="getEventColor"
        :event-text-color="getEventTextColor"
        @click:date="dateSelected"
        @change="getPrices"
      ></v-calendar>
    </v-sheet>
  </div>
</template>

<script>
export default {
  data: () => ({
    type: "month",
    mode: "stack",
    weekday: [0, 1, 2, 3, 4, 5, 6],
    value: "",
    events: [],
    color: "white",
    textcolors: ["#72A971", "#ED8A53"],
    names: ["+30,000", "+500", "+7,000", "-30", "-5", "-700"],
  }),
  methods: {
    getPrices({ start, end }) {
      const events = [];

      const yearmonth = start.date.substr(0, 8);
      const startDate = start.date.slice(-2);
      const endDate = end.date.slice(-2);

      const eventCount = this.rnd(3, 8);

      for (let i = 0; i < eventCount; i++) {
        const date = this.rnd(parseInt(startDate), parseInt(endDate));
        const price = this.names[this.rnd(0, this.names.length - 1)];

        events.push({
          name: price,
          start: yearmonth + date,
          color: this.color,
          textcolor: this.textcolors[this.rndcolor(parseInt(price))],
        });
      }

      this.events = events;
    },
    getWeekDayFormat(date) {
      const weekDayFormat = ["S", "M", "T", "W", "T", "F", "S"];
      return weekDayFormat[date.weekday];
    },
    getEventColor(event) {
      return event.color;
    },
    getEventTextColor(event) {
      return event.textcolor;
    },
    dateSelected(value) {
      this.$emit("dateSelected");
      console.log(value.date);
      console.log(value.year);
      console.log(value.month);
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    rndcolor(a) {
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
  padding-top: 6%;
  padding-bottom: 4%;
  text-align: center;
  font-size: 1rem;
  font-weight: 900;
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
  margin-bottom: 5%;
  display: flex;
  justify-content: center;
}
.spacer {
  align-self: center;
  text-align: center;
  font-size: 1.2rem;
  font-weight: 900;
  color: #5b574b;
}
.calendar {
  text-align: center;
  font-weight: bold;
}
.theme--light.v-calendar-weekly {
  border: 30px;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__head-weekday {
  padding-bottom: 2%;
  border: 0px;
  font-size: 1rem;
  color: #5b574b;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__head-weekday.v-outside {
  background-color: white;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__day {
  border: 0px;
}
.theme--light.v-calendar-weekly .v-calendar-weekly__day.v-outside {
  visibility: hidden;
}
.v-calendar-weekly__day-label .v-btn {
  margin-top: 2%;
  font-size: 1rem;
}
.v-calendar .v-event {
  font-size: 0.7rem;
}
.v-application .pl-1 {
  padding: 0 !important;
}
</style>
