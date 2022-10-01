package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeLike;
import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import idle.molaeng_back.recipe.model.response.RecipeLikeResponse;
import idle.molaeng_back.recipe.model.response.RecipeListResponse;

import java.util.List;

public interface RecipeLikeService {

    List<RecipeListResponse> getRecipeLikeList(long userId);
    RecipeLikeResponse registRecipeLike(RecipeLikeRequest recipeLikeRequest);
    RecipeLikeResponse deleteRecipeLike(RecipeLikeRequest recipeLikeRequest);

    double calAvgScore(Recipe recipe);
}
