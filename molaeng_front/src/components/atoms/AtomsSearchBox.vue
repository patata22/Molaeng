<template>
  <div id="searchTextField">
    <v-text-field
      :placeholder="searchword"
      v-model="keyword"
      color="dark"
      solo
      dense
      rounded
      @input="sendKeyword"
      v-on:keyup.enter="submit"
      append-icon="mdi-magnify"
    >
    </v-text-field>
  </div>
</template>

<script>
export default {
  name: "SearchBox",
  computed: {
    newkeyword: function () {
      return this.keyword;
    },
  },
  props: ["searchword"],
  data: () => ({
    keyword: "",
  }),
  methods: {
    startSTT() {
      console.log("mic on!");
    },
    sendKeyword() {
      this.$emit("change", this.keyword);
    },
    submit() {
      this.$emit("submit");
    },
  },
};
</script>

<style>
#searchTextField .v-input__slot {
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
  max-width: 300px;
  background-color: white;
  display: flex;
}
.v-messages {
  min-height: 0px;
}
.v-text-field.v-text-field--enclosed .v-text-field__details {
  margin-bottom: 0px;
}
.v-text-field__details {
  min-height: 0px;
}
</style>
