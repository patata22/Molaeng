package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.response.RecipeRes;

public interface RecipeService {
    //레시피 정보 가져오기
    RecipeRes readRecipeById(long userId, long recipeId);
}
