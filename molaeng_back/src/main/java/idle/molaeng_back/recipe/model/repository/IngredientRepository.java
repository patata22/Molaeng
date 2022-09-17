package idle.molaeng_back.recipe.model.repository;

import idle.molaeng_back.recipe.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // 전체 재료 목록 조회
    @Override
    List<Ingredient> findAll();

    // 재료의 가격 정보 조회
    Ingredient findById(long ingredientId);


    // 레시피의 재료 가격 정보 조회: 레시피에 필요한 재료 무게대로 가격 계산해서 넘겨주기
}
