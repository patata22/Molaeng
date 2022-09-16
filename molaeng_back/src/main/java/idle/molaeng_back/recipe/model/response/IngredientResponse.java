package idle.molaeng_back.recipe.model.response;

import idle.molaeng_back.recipe.model.entity.Ingredient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientResponse {

    public static class IngredientPreview {
        private long ingredientId;
        private String ingreientName;

        @Builder
        public IngredientPreview(Ingredient ingredient) {
            this.ingredientId = ingredient.getIngredientId();
            this.ingreientName = ingredient.getIngredientName();
        }
    }

    public static class IngredientInfo {
        private long ingredientId;
        private String ingredientName;
        private int needWeight;
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
        private long ingredientId;
        private String ingredientName;
        private int ingredientPrice;
        private int ingredientWeight;
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
