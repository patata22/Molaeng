package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import idle.molaeng_back.recipe.model.entity.RecipeLike;
import idle.molaeng_back.recipe.model.repository.RecipeLikeRepository;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import idle.molaeng_back.recipe.model.response.RecipeLikeResponse;
import idle.molaeng_back.search.DTO.response.IngredientResDTO;
import idle.molaeng_back.search.DTO.response.RecipeResDTO;
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
    public List<RecipeResDTO> getRecipeLikeList(long userId) {
        List<RecipeLike> recipeLikeList = recipeLikeRepository.findAllByUserUserId(userId);
        List<RecipeResDTO> resultList = new ArrayList<>();
        double avgScore;

        for(int i = 0; i < recipeLikeList.size(); i++){
            Recipe recipe = recipeRepository.findByRecipeId(recipeLikeList.get(i).getRecipe().getRecipeId());
            int isLiked = recipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, recipe.getRecipeId());
            avgScore = calAvgScore(recipe);
            RecipeResDTO response = RecipeResDTO
                    .builder()
                    .recipeId(recipe.getRecipeId())
                    .recipeName(recipe.getRecipeName())
                    .recipeKcal(recipe.getRecipeKcal())
                    .ingredientList(IngredientToDTO(recipe))
                    .avgScore(avgScore)
                    .recipeImage(recipe.getRecipeImage())
                    .isLiked(isLiked)
                    .build();
            resultList.add(response);
        }

        return resultList;
    }

    //관심 레시피 등록, 삭제 - 메서드명 수정
    @Override
    public RecipeLikeResponse registRecipeLike(long userId, long recipeId) {
        User user = userRepository.findByUserId(userId);
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);

        RecipeLike recipeLike = RecipeLike.builder().user(user).recipe(recipe).build();
        recipeLikeRepository.save(recipeLike);

        return new RecipeLikeResponse(recipeId);
    }

    @Transactional
    @Override
    public RecipeLikeResponse deleteRecipeLike(long userId, long recipeId) {
        User user = userRepository.findByUserId(userId);
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);

        recipeLikeRepository.deleteByUserUserIdAndRecipeRecipeId(userId, recipeId);

        return new RecipeLikeResponse(recipeId);
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

        double avgScore = Math.round(((double) sum * 10 / countScore) / 10);

        return avgScore;
    }

    private List<IngredientResDTO> IngredientToDTO(Recipe recipe) {
        List<IngredientResDTO> tempIngRes = new ArrayList<>();
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredientList()) {
            tempIngRes.add(new IngredientResDTO(recipeIngredient.getIngredient().getIngredientId(), recipeIngredient.getIngredient().getIngredientName()));
        }
        return tempIngRes;
    }
}
