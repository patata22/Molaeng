package idle.molaeng_back.search.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {

    List<Recipe> searchRecipeByIngredient(List<Long> ingredientIdList);
    List<Recipe> findRecipeByName(String keyword);
}
