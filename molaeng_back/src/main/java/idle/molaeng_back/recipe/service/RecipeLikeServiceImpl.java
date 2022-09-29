package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeLike;
import idle.molaeng_back.recipe.model.repository.RecipeLikeRepository;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import idle.molaeng_back.recipe.model.response.RecipeLikeResponse;
import idle.molaeng_back.user.model.User;
import idle.molaeng_back.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeLikeServiceImpl implements RecipeLikeService{

    @Autowired
    private final RecipeLikeRepository recipeLikeRepository;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RecipeRepository recipeRepository;

    public RecipeLikeServiceImpl(RecipeLikeRepository recipeLikeRepository, UserRepository userRepository, RecipeRepository recipeRepository) {
        this.recipeLikeRepository = recipeLikeRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeLikeResponse registReviewLike(RecipeLikeRequest recipeLikeRequest) {
        User user = userRepository.findByUserId(recipeLikeRequest.getUserId());
        Recipe recipe = recipeRepository.findByRecipeId(recipeLikeRequest.getRecipeId());

        RecipeLike recipeLike = RecipeLike.builder().user(user).recipe(recipe).build();
        recipeLikeRepository.save(recipeLike);

        return new RecipeLikeResponse(recipeLikeRequest.getRecipeId());
    }

    @Override
    public void deleteReviewLike(RecipeLikeRequest recipeLikeRequest) {

    }
}
