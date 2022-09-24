package idle.molaeng_back.recipe.model.repository;

import idle.molaeng_back.recipe.model.entity.RecipeSubIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeSubIngredientRepository extends JpaRepository<RecipeSubIngredient, Long> {
    List<RecipeSubIngredient> findAllByRecipeRecipeId(long recipeId);
}
