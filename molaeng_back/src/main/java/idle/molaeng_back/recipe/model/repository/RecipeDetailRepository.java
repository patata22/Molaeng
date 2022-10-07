package idle.molaeng_back.recipe.model.repository;

import idle.molaeng_back.recipe.model.entity.RecipeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeDetailRepository extends JpaRepository<RecipeDetail, Long> {
    List<RecipeDetail> findAllByRecipeRecipeId(long recipeId);
}
