package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeSubIngredientRes {
    //부재료명
    String subIngredientName;
    //필요무게+무게단위
    String weightUnit;

    public RecipeSubIngredientRes(String subIngredientName, String weightUnit) {
        this.subIngredientName = subIngredientName;
        this.weightUnit = weightUnit;
    }
}
