package idle.molaeng_back.recipe.model.response;

import idle.molaeng_back.recipe.model.entity.Ingredient;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientResponse {

    public static class IngredientPreview {
        @ApiParam("재료 id")
        private long ingredientId;
        @ApiParam("재료명")
        private String ingreientName;

        @Builder
        public IngredientPreview(Ingredient ingredient) {
            this.ingredientId = ingredient.getIngredientId();
            this.ingreientName = ingredient.getIngredientName();
        }
    }

    public static class IngredientInfo {
        @ApiParam("재료 id")
        private long ingredientId;
        @ApiParam("재료명")
        private String ingredientName;
        @ApiParam("레시피를 만드는 데 필요한 재료 무게")
        private int needWeight;
        @ApiParam("필요한 재료의 무게 단위")
        private String weightUnit;

        @Builder
        public IngredientInfo(Ingredient ingredient) {
            this.ingredientId = ingredient.getIngredientId();
            this.ingredientName = ingredient.getIngredientName();
            this.needWeight = ingredient.getIngredientWeight();
            this.weightUnit = ingredient.getIngredientUnit();
        }
    }

    public static class IngredientPrice {
        @ApiParam("재료 id")
        private long ingredientId;
        @ApiParam("재료명")
        private String ingredientName;
        @ApiParam("기준 무게 당 재료 가격")
        private double ingredientPrice;
        @ApiParam("기준 무게")
        private int ingredientWeight;
        @ApiParam("기준 무게의 무게단위")
        private String ingredientUnit;

        @Builder
        public IngredientPrice(Ingredient ingredient){
            this.ingredientId = ingredient.getIngredientId();
            this.ingredientName = ingredient.getIngredientName();
            this.ingredientPrice = ingredient.getIngredientPrice();
            this.ingredientWeight = ingredient.getIngredientWeight();
            this.ingredientUnit = ingredient.getIngredientUnit();
        }


    }
}
