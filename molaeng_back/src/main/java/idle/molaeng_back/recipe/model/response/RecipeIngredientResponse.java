package idle.molaeng_back.recipe.model.response;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import lombok.Builder;

public class RecipeIngredientResponse {

    public static class RecipeIngredientInfo {
        // 레시피에 필요한 재료들 조회
        private long ingredientId;
        private String ingredientName;
        private int needWeight;
        private String weightUnit;

        @Builder
        public RecipeIngredientInfo(RecipeIngredient recipeIngredient){
            this.ingredientId = recipeIngredient.getIngredient().getIngredientId();
            this.ingredientName = recipeIngredient.getIngredient().getIngredientName();
            this.needWeight = recipeIngredient.getNeedWeight();
            this.weightUnit = recipeIngredient.getWeightUnit();
        }
    }

    // 레시피에 필요한 재료+가격정보
    public static class RecipeIngredientPriceInfo {
        private long ingredientId;
        private String ingredientName;
        private int price;

        @Builder
        public RecipeIngredientPriceInfo(RecipeIngredient recipeIngredient, int price) {
            this.ingredientId = recipeIngredient.getIngredient().getIngredientId();
            this.ingredientName = recipeIngredient.getIngredient().getIngredientName();
            this.price = price;
        }
    }


}
