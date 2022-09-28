package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import idle.molaeng_back.recipe.model.response.RecipeLikeResponse;

public interface RecipeLikeService {
    RecipeLikeResponse registReviewLike(RecipeLikeRequest recipeLikeRequest);
    void deleteReviewLike(RecipeLikeRequest recipeLikeRequest);
}
