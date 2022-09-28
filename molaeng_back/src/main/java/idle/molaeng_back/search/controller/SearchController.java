package idle.molaeng_back.search.controller;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.search.DTO.request.SearchRecipeByIngredientReqDTO;
import idle.molaeng_back.search.DTO.response.RecipeNameResDTO;
import idle.molaeng_back.search.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/ingredient")
    public ResponseEntity searchByIngredient(@RequestBody List<SearchRecipeByIngredientReqDTO> request){
        List<Long> ingredientIdList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            for (SearchRecipeByIngredientReqDTO dto : request) {
                ingredientIdList.add(dto.getRecipeId());
            }
            List<Recipe> result = searchService.searchRecipeByIngredient(ingredientIdList);
            resultMap.put("message", "success");
            resultMap.put("recipeList", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "재료로 검색에서 에러났어어어");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recipe/{keyword}")
    //autocomplete로 갈거면 상관이 없다;
    public ResponseEntity searchByKeyword(@PathVariable String keyword, @RequestParam("page") int page){
        // 페이징 추가해야되니까 쿼리파라미터로 가는게 맞을듯
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<Recipe> result = searchService.findRecipeByName(keyword);
            resultMap.put("message", "success");
            resultMap.put("recipeList", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e ){
            resultMap.put("message", "레시피 이름검색에서 에러");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity searchAllRecipe(){
        Map<String, Object> resultMap = new HashMap();
        try{
            List<RecipeNameResDTO> Dtos = searchService.searchAllRecipe();
            resultMap.put("message", "success");
            resultMap.put("result", Dtos);
            return new ResponseEntity(resultMap, HttpStatus.OK);

        }catch(Exception e){
            resultMap.put("message", "레시피 전체 조회에서 에러났는데????");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }
}
