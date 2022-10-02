package idle.molaeng_back.recipe.controller;

import idle.molaeng_back.recipe.model.response.IngredientResponse;
import idle.molaeng_back.recipe.model.response.RecipeIngredientResponse;
import idle.molaeng_back.recipe.service.IngredientService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IngredientController {

    private static final Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    IngredientService ingredientService;

    @ApiOperation(value="레시피에 필요한 재료들 조회", notes = "레시피 id를 이용하여 레시피를 만드는 데 필요한 재료의 정보들을 불러온다.")
    @GetMapping("/recipe/ingredient/{recipeId}")
    public ResponseEntity getRecipeIngredients(@PathVariable Long recipeId) {
        Map<String, Object> result = new HashMap<>();
        result.put("ingredientList", ingredientService.searchIngredientByRecipeId(recipeId));
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value="레시피를 만드는 데 필요한 가격 조회",
            notes = "레시피 id를 이용하여 레시피를 만드는 데 필요한 재료의 가격 정보, 서울 내 최고&최저가, 서울 평균가, 우리 지역의 평균가격을 불러온다.")
    @GetMapping("/recipe/priceinfo/{recipeId}")
    public ResponseEntity getRecipeMakingPrice(@PathVariable Long recipeId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("ingredientList", ingredientService.searchIngredientPriceByRecipeId(recipeId));
            // 서울 내 최고&최저가, 평균가, 우리 지역 가격 추가 필요
        } catch (Throwable e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value="재료 목록 조회", notes = "등록된 전체 재료 목록을 불러온다.")
    @GetMapping("/search")
    public ResponseEntity getAllIngredient() {
        Map<String, Object> result = new HashMap<>();
        result.put("ingredientList", ingredientService.searchAllIngredient());
        return ResponseEntity.ok().body(result);
    }
}