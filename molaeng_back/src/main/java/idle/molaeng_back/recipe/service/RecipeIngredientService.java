package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import idle.molaeng_back.recipe.model.response.RecipeIngredientRes;

import java.util.List;

public interface RecipeIngredientService {
    //recipeId로 레시피 재료 정보 리스트로 받아오기
    List<RecipeIngredientRes> readRecipeIngredientByRecipeId(long recipeId);
}
