package idle.molaeng_back.recipe.model.repository;

import idle.molaeng_back.recipe.model.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    //레시피 아이디로 해당 레시피 가져오기
    Recipe findByRecipeId(long recipeId);

    List<Recipe> findAll();

    Slice<Recipe> findAllByRecipeNameContains(String keyWord, Pageable pageable);
    Page<Recipe> findAll(Pageable pageable);
}
