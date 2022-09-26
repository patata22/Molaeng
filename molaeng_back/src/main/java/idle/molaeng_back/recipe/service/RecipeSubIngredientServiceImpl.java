package idle.molaeng_back.recipe.service;

import idle.molaeng_back.recipe.model.entity.RecipeSubIngredient;
import idle.molaeng_back.recipe.model.repository.RecipeSubIngredientRepository;
import idle.molaeng_back.recipe.model.response.RecipeIngredientRes;
import idle.molaeng_back.recipe.model.response.RecipeSubIngredientRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeSubIngredientServiceImpl implements RecipeSubIngredientService{
    private final RecipeSubIngredientRepository recipeSubIngredientRepository;

    @Autowired
    public RecipeSubIngredientServiceImpl(RecipeSubIngredientRepository recipeSubIngredientRepository) {
        this.recipeSubIngredientRepository = recipeSubIngredientRepository;
    }

    @Override
    public List<RecipeSubIngredientRes> readRecipeSubIngredientByRecipeId(long recipeId) {
        List<RecipeSubIngredient> recipeSubIngredientList = recipeSubIngredientRepository.findAllByRecipeRecipeId(recipeId);

        ArrayList<RecipeSubIngredientRes> recipeSubIngredientResList = new ArrayList<>();

        for (RecipeSubIngredient recipeSubIngredient:recipeSubIngredientList) {
            RecipeSubIngredientRes recipeSubIngredientRes = RecipeSubIngredientRes.builder()
                    .subIngredientName(recipeSubIngredient.getSubIngredientName())
                    .weightUnit(recipeSubIngredient.getWeightUnit())
                    .build();

            recipeSubIngredientResList.add(recipeSubIngredientRes);
        }

        return recipeSubIngredientResList;
    }
}
