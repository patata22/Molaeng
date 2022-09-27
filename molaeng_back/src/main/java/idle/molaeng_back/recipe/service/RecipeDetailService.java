package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.RecipeDetail;
import idle.molaeng_back.recipe.model.response.RecipeDetailRes;

import java.util.List;

public interface RecipeDetailService {
    //recipeId로 레시피 상세정보(조리방법) 리스트로 받아오기
    List<RecipeDetailRes> readRecipeDetailByRecipeId(long recipeId);
}
