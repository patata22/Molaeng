<template>
  <div id="searchTextField" class="mt-5">
    <v-text-field
      :placeholder="searchword"
      v-model="keyword"
      color="dark"
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
  box-shadow: 0px 5px 10px rgb(91 87 75 / 20%);
  max-width: 300px;
  margin: auto;
}
</style>
