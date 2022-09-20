package idle.molaeng_back.search.controller;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.search.DTO.request.SearchRecipeByIngredientReqDTO;
import idle.molaeng_back.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/ingredient")
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

    @GetMapping("/recipe")
    public ResponseEntity searchByKeyword(@RequestBody String keyword){
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
}
