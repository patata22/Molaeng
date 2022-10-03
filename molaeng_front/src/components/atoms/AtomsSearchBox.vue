<template>
  <div id="searchTextField" class="mt-5">
    <v-text-field
      :placeholder="searchword"
      v-model="keyword"
      color="default"
      solo
      dense
      rounded
      append-icon="mdi-microphone"
      @input="sendKeyword"
      @click:append="startSTT"
    ></v-text-field>
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
  },
};
</script>

<style>
#searchTextField .v-input__slot {
  box-shadow: 0px 4px 10px #cecdc9;
  max-width: 351px;
  margin: auto;
}
</style>
