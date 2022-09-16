package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import idle.molaeng_back.recipe.model.repository.IngredientRepository;
import idle.molaeng_back.recipe.model.repository.RecipeIngredientRepository;
import idle.molaeng_back.recipe.model.response.IngredientResponse;
import idle.molaeng_back.recipe.model.response.RecipeIngredientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private static final Logger logger = LoggerFactory.getLogger(RecipeIngredientServiceImpl.class);

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

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
    public List<RecipeIngredientResponse.RecipeIngredientPriceInfo> searchIngredientPriceByRecipeId(long recipeId) {
        List<RecipeIngredient> ingredients = recipeIngredientRepository.findAllByRecipe_RecipeId(recipeId);
        List<RecipeIngredientResponse.RecipeIngredientPriceInfo> result = new ArrayList<>();
//        for (RecipeIngredient ingredient : ingredients) {
//            IngredientResponse.IngredientPrice ingredientPrice = ingredientRepository.findById(ingredient.getIngredient().getIngredientId())
//                    .orElseThrow(()->new Exception());
//            result.add(new RecipeIngredientResponse.RecipeIngredientPriceInfo(ingredient, ))
//        }
//        return null;
        return result;
    }
}
