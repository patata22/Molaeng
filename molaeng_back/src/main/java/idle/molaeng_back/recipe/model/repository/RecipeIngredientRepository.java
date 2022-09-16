package idle.molaeng_back.recipe.model.repository;

import idle.molaeng_back.recipe.model.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository {

    // 레시피의 재료 정보 조회
    List<RecipeIngredient> findAllByRecipe_RecipeId(long recipeId);
}
