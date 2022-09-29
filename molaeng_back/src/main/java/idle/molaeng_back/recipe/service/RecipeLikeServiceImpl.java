package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeLike;
import idle.molaeng_back.recipe.model.repository.RecipeLikeRepository;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import idle.molaeng_back.recipe.model.response.RecipeLikeResponse;
import idle.molaeng_back.recipe.model.response.RecipeListResponse;
import idle.molaeng_back.user.model.User;
import idle.molaeng_back.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeLikeServiceImpl implements RecipeLikeService{

    @Autowired
    private final RecipeLikeRepository recipeLikeRepository;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeLikeServiceImpl(RecipeLikeRepository recipeLikeRepository, UserRepository userRepository, RecipeRepository recipeRepository) {
        this.recipeLikeRepository = recipeLikeRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public List<RecipeListResponse> getRecipeLikeList(long userId) {
        List<RecipeLike> recipeLikeList = recipeLikeRepository.findAllByUserUserId(userId);
        List<RecipeListResponse> resultList = new ArrayList<>();
        boolean isLiked;
        double avgScore;

        for(int i = 0; i < recipeLikeList.size(); i++){
            Recipe recipe = recipeRepository.findByRecipeId(recipeLikeList.get(i).getRecipe().getRecipeId());
            isLiked = (recipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, recipe.getRecipeId())) == 1 ? true : false;
            System.out.println((recipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, recipe.getRecipeId())));
            avgScore = calAvgScore(recipe);
            RecipeListResponse response = RecipeListResponse
                    .builder()
                    .recipeId(recipe.getRecipeId())
                    .recipeName(recipe.getRecipeName())
                    .recipeKcal(recipe.getRecipeKcal())
                    .needPrice(111) // 얼마나 더 필요한지에 대해서는 다른 곳에서 계산한 값을 넣어줘야 함.
                    .avgScore(avgScore)
                    .recipeImg(recipe.getRecipeImage())
                    .isliked(isLiked)
                    .build();
            resultList.add(response);
        }

        return resultList;
    }

    @Override
    public RecipeLikeResponse registReviewLike(RecipeLikeRequest recipeLikeRequest) {
        User user = userRepository.findByUserId(recipeLikeRequest.getUserId());
        Recipe recipe = recipeRepository.findByRecipeId(recipeLikeRequest.getRecipeId());

        RecipeLike recipeLike = RecipeLike.builder().user(user).recipe(recipe).build();
        recipeLikeRepository.save(recipeLike);

        return new RecipeLikeResponse(recipeLikeRequest.getRecipeId());
    }

    @Transactional
    @Override
    public RecipeLikeResponse deleteReviewLike(RecipeLikeRequest recipeLikeRequest) {
        User user = userRepository.findByUserId(recipeLikeRequest.getUserId());
        Recipe recipe = recipeRepository.findByRecipeId(recipeLikeRequest.getRecipeId());

        recipeLikeRepository.deleteByUserUserIdAndRecipeRecipeId(recipeLikeRequest.getUserId(), recipeLikeRequest.getRecipeId());

        return new RecipeLikeResponse(recipeLikeRequest.getRecipeId());
    }

    // recipe의 평균 별점을 계산하는 메서드
    @Override
    public double calAvgScore(Recipe recipe){
        int [] scoreList = new int[5];
        long sum = 0;
        long countScore = 0;

        scoreList[0] = recipe.getOneScore();
        scoreList[1] = recipe.getTwoScore();
        scoreList[2] = recipe.getThreeScore();
        scoreList[3] = recipe.getFourScore();
        scoreList[4] = recipe.getFiveScore();

        for(int score = 1; score <= scoreList.length; score++){
            sum += scoreList[score-1] * score;
            countScore += scoreList[score-1];
        }

        return (double) sum / countScore;
    }
}
