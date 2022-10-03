<template>
  <div id="ingredientCard">
    <v-card id="card" class="mx-auto mb-9 rounded-lg">
      <v-card-actions id="title" :class="{ 'rounded-lg': !show }">
        <v-card-title class="dark--text font-weight-bold" text>
          {{ ingredientCategory.ingredientTitle }}
        </v-card-title>

        <v-spacer></v-spacer>

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-card-text>
            <ingredient-tag-list
              :ingredientList="ingredientCategory.ingredientList"
              :keyWord="keyWord"
            />
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </div>
</template>

<script>
import IngredientTagList from "../molecules/MoleculesIngredientTagList.vue";

export default {
  name: "IngredientCard",
  props: {
    ingredientCategory: Object,
    keyWord: String,
  },
  components: {
    IngredientTagList,
  },
  data: () => ({
    show: false,
  }),
};
</script>

<style>
.v-card,
table {
  width: 86%;
  max-width: 351px;
}

.v-card {
  margin-top: 7%;
}

#title {
  background-color: #fef3c6;
}
#card {
  box-shadow: 0px 4px 10px #cecdc9;
  max-width: 351px;
}
#ingredientCard #title {
  height: 60px;
}
</style>
