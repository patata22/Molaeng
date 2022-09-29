package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.repository.RecipeLikeRepository;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.recipe.model.response.RecipeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;
    private final RecipeLikeRepository recipeLikeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeLikeRepository recipeLikeRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeLikeRepository = recipeLikeRepository;
    }

    @Override
    public RecipeRes readRecipeById(long userId, long recipeId) {
        System.out.println("readRecipeById로 들어옴");

        //recipeId로 해당 레시피 정보 가져오기
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);

        System.out.println("가져온 레시피 : " + recipe.toString());

        //recipeId와 userId로 RecipeLike 테이블에서 해당 데이터 찾기
        //0이면 없다는 거니까 false, 1이면 있다는 거니까 true
        boolean isLiked= recipeLikeRepository.countByUserUserIdAndRecipeRecipeId(userId, recipeId)==0?false:true;

        System.out.println("isLiked : " + isLiked);

        RecipeRes recipeRes = RecipeRes.builder()
                .recipeId(recipeId)
                .recipeName(recipe.getRecipeName())
                .recipeKcal(recipe.getRecipeKcal())
                .recipeImg(recipe.getRecipeImage())
                .isLiked(isLiked)
                .build();

        System.out.println("recipeRes : " + recipeRes.toString());

        return recipeRes;
    }
}
