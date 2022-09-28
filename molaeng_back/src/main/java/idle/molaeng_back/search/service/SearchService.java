package idle.molaeng_back.search.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.search.DTO.response.RecipeNameResDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SearchService {

    List<Recipe> searchRecipeByIngredient(List<Long> ingredientIdList);
    List<Recipe> findRecipeByName(String keyword);

    List<RecipeNameResDTO> searchAllRecipe();
}
