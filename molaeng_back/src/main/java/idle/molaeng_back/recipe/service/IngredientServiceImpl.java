package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.Ingredient;
import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import idle.molaeng_back.recipe.model.repository.IngredientRepository;
import idle.molaeng_back.recipe.model.repository.RecipeIngredientRepository;
import idle.molaeng_back.recipe.model.response.IngredientResponse;
import idle.molaeng_back.recipe.model.response.RecipeIngredientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static final Logger logger = LoggerFactory.getLogger(IngredientServiceImpl.class);

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    //전체 재료 목록 반환(검색창용)
    @Override
    public List<IngredientResponse.IngredientPreview> searchAllIngredient() {
        List<IngredientResponse.IngredientPreview> result = new ArrayList<>();
        List<Ingredient> ingredients = ingredientRepository.findAll();
        for (Ingredient ingredient : ingredients) {
            result.add(new IngredientResponse.IngredientPreview(ingredient));
        }

        return result;
    }

    // 레시피에 필요한 재료 목록 반환
    @Override
    public List<RecipeIngredientResponse.RecipeIngredientInfo> searchIngredientByRecipeId(long recipeId) {
        List<RecipeIngredient> ingredients = recipeIngredientRepository.findAllByRecipe_RecipeId(recipeId);
        List<RecipeIngredientResponse.RecipeIngredientInfo> result = new ArrayList<>();
        for (RecipeIngredient ingredient : ingredients) {
            result.add(new RecipeIngredientResponse.RecipeIngredientInfo(ingredient));
        }
        return result;
    }

    // 레시피에 필요한 재료들의 가격 정보 반환
    @Override
    public List<RecipeIngredientResponse.RecipeIngredientPriceInfo> searchIngredientPriceByRecipeId(long recipeId) throws Throwable {
        // 가장 먼저 레시피- 재료 테이블에서 필요한 재료 목록 불러오기
        // 필요한 재료 목록들의 재료id로 재료 가격 조회
        // 레시피-재료 테이블에 적힌 무게대로 가격 환산
//        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findAllByRecipe_RecipeId(recipeId);
//        List<RecipeIngredientResponse.RecipeIngredientPriceInfo> result = new ArrayList<>();
//        for (RecipeIngredient recipeIngredient : recipeIngredients) {
//            Ingredient ingredient = recipeIngredient.getIngredient();
//            result.add(new RecipeIngredientResponse.RecipeIngredientPriceInfo(recipeIngredient, ingredient.pricePerWeight(recipeIngredient.getNeedWeight(),recipeIngredient.getWeightUnit())));
//        }
//
//        return result;
        return null;
    }


}
