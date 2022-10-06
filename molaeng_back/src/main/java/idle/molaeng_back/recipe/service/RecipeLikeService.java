package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import idle.molaeng_back.recipe.model.response.RecipeLikeResponse;
import idle.molaeng_back.search.DTO.response.RecipeResDTO;

import java.util.List;

public interface RecipeLikeService {

    List<RecipeResDTO> getRecipeLikeList(long userId);
    RecipeLikeResponse registRecipeLike(long userId, long recipeId);
    RecipeLikeResponse deleteRecipeLike(long userId, long recipeId);

    double calAvgScore(Recipe recipe);
}
