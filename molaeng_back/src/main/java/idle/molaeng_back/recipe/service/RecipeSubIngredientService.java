package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.response.RecipeSubIngredientRes;

import java.util.List;

public interface RecipeSubIngredientService {
    List<RecipeSubIngredientRes> readRecipeSubIngredientByRecipeId(long recipeId);
}
