<template>
  <div id="searchTextField" class="mt-5">
    <v-text-field
      placeholder="레시피를 검색해주세요"
      v-model="keyword"
      color="default"
      solo
      dense
      rounded
      append-icon="mdi-microphone"
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
  data: () => ({
    keyword: "",
  }),
  methods: {
    startSTT() {
      console.log("mic on!");
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
