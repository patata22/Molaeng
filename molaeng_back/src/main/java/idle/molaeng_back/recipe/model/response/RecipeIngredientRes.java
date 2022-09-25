package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeIngredientRes {
    long ingredientId;
    String ingredientName;
    //필요무게가 erd랑 entity에는 int인데 실제 데이터는 소수점도 있는데 -> double로 수정
    double needWeight;
    String weightUnit;

    public RecipeIngredientRes(long ingredientId, String ingredientName, double needWeight, String weightUnit) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.needWeight = needWeight;
        this.weightUnit = weightUnit;
    }
}
