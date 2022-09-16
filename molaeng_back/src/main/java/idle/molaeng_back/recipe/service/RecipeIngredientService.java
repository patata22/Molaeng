package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.response.RecipeIngredientResponse;

import java.util.List;

public interface RecipeIngredientService {

    // 해당 레시피에 사용되는 재료들을 반환
    List<RecipeIngredientResponse.RecipeIngredientInfo> searchIngredientByRecipeId(long recipeId);

    // 해당 레시피에 사용되는 재료들의 가격을 반환
    List<RecipeIngredientResponse.RecipeIngredientPriceInfo> searchIngredientPriceByRecipeId(long recipeId);
}
