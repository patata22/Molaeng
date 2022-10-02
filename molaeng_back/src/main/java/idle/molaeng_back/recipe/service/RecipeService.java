package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.response.RecipeRes;
import idle.molaeng_back.search.DTO.response.RecipeResDTO;

import java.util.List;

public interface RecipeService {
    //레시피 정보 가져오기
    RecipeRes readRecipeById(long userId, long recipeId);

    public List<RecipeResDTO> getRecipeList(List<Long> RecipeIdList, long userId);
}
