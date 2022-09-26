package idle.molaeng_back.recipe.model.repository;

import idle.molaeng_back.recipe.model.entity.RecipeLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Long> {
    //userId와 recipeId로 사용자가 레시피에 좋아요를 눌렀는지 확인
    int countByRecipeRecipeIdAndUserUserId(long userId, long recipeId);
}
