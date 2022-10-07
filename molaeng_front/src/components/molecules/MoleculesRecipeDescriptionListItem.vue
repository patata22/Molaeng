<template>
  <div>
    <div tabindex="-1" class="v-stepper__step v-stepper__step--active">
      <span class="v-stepper__step__step primary">{{ order }}</span>
      <div class="v-stepper__label" style="line-height: 140%">
        {{ content }}
      </div>
    </div>
    <div class="v-stepper_content">
      <div class="v-stepper__wrapper" style="height: auto">
        <v-img
          v-if="detailImg != ' '"
          :lazy-src="detailImg"
          height="auto"
          width="100%"
          :src="detailImg"
          class="mb-5"
        ></v-img>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RecipeDescriptionListItem",
  props: {
    // 조리순서, 이미지, 내용을 받아옴
    order: Number,
    detailImg: String,
    content: String,
  },
  data: () => ({}),
};
</script>

<style></style>
