package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import idle.molaeng_back.recipe.model.repository.RecipeIngredientRepository;
import idle.molaeng_back.recipe.model.response.RecipeIngredientRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecipeIngredientServiceImpl implements RecipeIngredientService{
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public List<RecipeIngredientRes> readRecipeIngredientByRecipeId(long recipeId) {
        List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findAllByRecipe_RecipeId(recipeId);

        ArrayList<RecipeIngredientRes> recipeIngredientResList = new ArrayList<>();
        for (RecipeIngredient recipeIngredient : recipeIngredientList) {
            //RecipeIngredient 중계 테이블은 Ingredient 테이블과 연관관계가 있으므로 getIngredient를 통해 Ingredient 객체를 가져올 수 있다.
            //거기서 IngredientId, IngredientName 꺼내기
            long ingredientId = recipeIngredient.getIngredient().getIngredientId();
            String ingredientName = recipeIngredient.getIngredient().getIngredientName();

            RecipeIngredientRes recipeIngredientRes = RecipeIngredientRes.builder()
                    .ingredientId(ingredientId)
                    .ingredientName(ingredientName)
                    .needWeight(recipeIngredient.getNeedWeight())
                    .weightUnit(recipeIngredient.getWeightUnit())
                    .build();

            recipeIngredientResList.add(recipeIngredientRes);
        }

        return recipeIngredientResList;
    }
}
