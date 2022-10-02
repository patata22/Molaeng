package idle.molaeng_back.recipe.model.response;

import idle.molaeng_back.recipe.model.entity.Ingredient;
import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeIngredientResponse {

    public static class RecipeIngredientInfo {
        // 레시피에 필요한 재료들 조회
        @ApiParam("재료 id")
        public long ingredientId;
        @ApiParam("재료명")
        public String ingredientName;
        @ApiParam("레시피를 만드는 데 필요한 재료 무게")
        public double needWeight;
        @ApiParam("재료의 무게 단위")
        public String weightUnit;

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
        @ApiParam("재료 id")
        public long ingredientId;
        @ApiParam("재료명")
        public String ingredientName;
        @ApiParam("레시피를 만드는 데 필요한 재료의 가격")
        public int price;

        @Builder
        public RecipeIngredientPriceInfo(Ingredient ingredient, int price) {
            this.ingredientId = ingredient.getIngredientId();
            this.ingredientName = ingredient.getIngredientName();
            this.price = price;
        }
    }


}
