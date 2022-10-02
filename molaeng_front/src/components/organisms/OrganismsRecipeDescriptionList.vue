<template>
  <v-stepper vertical>
    <!-- <div class="v-stepper v-stepper--vertical v-sheet theme--light"> -->
    <recipe-description-list-item
      v-for="(detail, index) in recipeDetailList"
      :key="index"
      :order="detail.recipeOrder"
      :detailImg="detail.recipeDetailImg"
      :content="detail.recipeContent"
    >
    </recipe-description-list-item>
    <under-bar-button :text="buttonText" @click.native="moveMolaeng" />
    <v-dialog v-model="dialog">
      <add-to-molaeng-button @cancel="cancel" />
    </v-dialog>
    <!-- </div> -->
  </v-stepper>
</template>

<script>
import RecipeDescriptionListItem from "../molecules/MoleculesRecipeDescriptionListItem.vue";
import AddToMolaengButton from "../atoms/AtomsAddToMolaengButton.vue";
import UnderBarButton from "../atoms/AtomsUnderBarButton.vue";

import API from "@/api/APIs";
const api = API;

export default {
  name: "RecipeDescriptionList",
  components: {
    RecipeDescriptionListItem,
    AddToMolaengButton,
    UnderBarButton,
  },
  props: {
    recipeId: String,
  },
  async created() {
    //레시피 조리방법
    this.recipeDetailList = await api.getRecipeDetail(this.recipeId);
  },
  data: () => ({
    //레시피 조리방법
    recipeDetailList: [],
    buttonText: "모랭일기에 기록하기",
    dialog: false,
  }),
  methods: {
    //registMolaeng으로 바꾸고 axios 연결해야 함
    async moveMolaeng() {
      this.dialog = true;
    },
    //emit으로 AddToMolaengButton에게서 전달받은 dialog
    cancel(value) {
      this.dialog = value;
    },
  },
};
</script>

<style></style>
