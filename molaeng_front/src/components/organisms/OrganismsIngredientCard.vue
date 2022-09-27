<template>
  <div>
    <v-card id="card" class="mx-auto mb-9 rounded-lg" max-width="351">
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
#title {
  background-color: #fef3c6;
  height: 60px;
}
#card {
  box-shadow: 0px 4px 10px #cecdc9;
}
</style>
