package idle.molaeng_back.recipe.model.repository;

import idle.molaeng_back.recipe.model.entity.RecipeLike;
import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Long> {
    //userId와 recipeId로 사용자가 레시피에 좋아요를 눌렀는지 확인
    int countByUserUserIdAndRecipeRecipeId(long userId, long recipeId);

    void deleteByUserUserIdAndRecipeRecipeId(long userId, long recipeId);

    List<RecipeLike> findAllByUserUserId(long userId);

}

