package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import idle.molaeng_back.recipe.model.repository.RecipeLikeRepository;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.recipe.model.response.RecipeRes;
import idle.molaeng_back.search.DTO.response.IngredientResDTO;
import idle.molaeng_back.search.DTO.response.RecipeResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeLikeRepository recipeLikeRepository;


    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeLikeRepository recipeLikeRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeLikeRepository = recipeLikeRepository;
    }

    @Override
    public RecipeRes readRecipeById(long userId, long recipeId) {
        //recipeId로 해당 레시피 정보 가져오기
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);

        //recipeId와 userId로 RecipeLike 테이블에서 해당 데이터 찾기
        //0이면 없다는 거니까 false, 1이면 있다는 거니까 true
        boolean isLiked = recipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, recipeId) == 0 ? false : true;

        RecipeRes recipeRes = RecipeRes.builder()
                .recipeId(recipeId)
                .recipeName(recipe.getRecipeName())
                .recipeKcal(recipe.getRecipeKcal())
                .recipeImg(recipe.getRecipeImage())
                .isLiked(isLiked)
                .build();

        return recipeRes;
    }

    @Override
    public List<RecipeResDTO> getRecipeList(List<Long> recipeIdList, long userId) {
        List<Recipe> recipeList = new ArrayList<>();
        for (long recipeId : recipeIdList) {
            recipeList.add(recipeRepository.findByRecipeId(recipeId));
        }
        List<RecipeResDTO> tempList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            long id = recipe.getRecipeId();
            tempList.add(RecipeResDTO.builder()
                    .recipeId(id)
                    .recipeName(recipe.getRecipeName())
                    .ingredientList(IngredientToDTO(recipe))
                    .recipeImage(recipe.getRecipeImage())
                    .recipeKcal(recipe.getRecipeKcal())
                    .isLiked(recipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, id))
                    .avgScore(getAvgScore(recipe))
                    .build());
        }
        return tempList;
    }

    private double getAvgScore(Recipe recipe) {
        int[] scoreCnt = new int[5];
        int total = 0;
        scoreCnt[0] = recipe.getOneScore();
        scoreCnt[1] = recipe.getTwoScore();
        scoreCnt[2] = recipe.getThreeScore();
        scoreCnt[3] = recipe.getFourScore();
        scoreCnt[4] = recipe.getFiveScore();
        int size = 0;
        for (int i = 0; i < 5; i++) {
            total += scoreCnt[i] * (i + 1);
            size += scoreCnt[i];
        }
        double avgScore = Math.round(((double) total * 10 / size) / 10);
        return avgScore;
    }

    //재료 추출용 메서드
    private List<IngredientResDTO> IngredientToDTO(Recipe recipe) {
        List<IngredientResDTO> tempIngRes = new ArrayList<>();
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredientList()) {
            tempIngRes.add(new IngredientResDTO(recipeIngredient.getIngredient().getIngredientId(), recipeIngredient.getIngredient().getIngredientName()));
        }
        return tempIngRes;
    }
}
