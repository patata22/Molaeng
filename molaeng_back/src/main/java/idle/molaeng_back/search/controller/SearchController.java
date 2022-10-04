package idle.molaeng_back.search.controller;

import idle.molaeng_back.search.DTO.request.SearchRecipeByIngredientReqDTO;
import idle.molaeng_back.search.DTO.request.SearchRecipeDTO;
import idle.molaeng_back.search.DTO.response.RecipeNameResDTO;
import idle.molaeng_back.search.DTO.response.SearchRecipeResDTO;
import idle.molaeng_back.search.service.SearchService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    //userId 더미처리 된것 고쳐야함!
    @PostMapping("/ingredient")
    public ResponseEntity searchByIngredient(@RequestBody SearchRecipeDTO data){
        Map<String, Object> resultMap = new HashMap<>();
        Pageable pageable = PageRequest.of(data.getPage(), data.getSize());
        int userId=1;
        List<Long> ingredientIdList = new ArrayList<>();
        try {
            for (SearchRecipeByIngredientReqDTO dto : data.getIngredientList()) {
                ingredientIdList.add(dto.getIngredientId());
            }
            SearchRecipeResDTO result = searchService.searchRecipeByIngredient(ingredientIdList, pageable, userId);
            resultMap.put("message", "success");
            resultMap.put("result", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "재료로 검색에서 에러났어어어");
            e.printStackTrace();
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/calory")
    public ResponseEntity searchByCalory(@RequestBody SearchRecipeDTO data, @RequestParam long userId, Pageable pageable){
        Map<String, Object> resultMap = new HashMap();

        try{
            List<Long> ingredientIdList = new ArrayList<>();
            for (SearchRecipeByIngredientReqDTO dto : data.getIngredientList()) {
                ingredientIdList.add(dto.getIngredientId());
            }
            SearchRecipeResDTO result = searchService.searchRecipeByCalory(ingredientIdList, pageable, userId);
            resultMap.put("result", result);
            resultMap.put("message","success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "검색에서 에러");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/score")
    public ResponseEntity searchByScore(@RequestBody SearchRecipeDTO data, @RequestParam long userId, Pageable pageable){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<Long> ingredientIdList = new ArrayList<>();
            for (SearchRecipeByIngredientReqDTO dto : data.getIngredientList()) {
                ingredientIdList.add(dto.getIngredientId());
            }
            SearchRecipeResDTO result = searchService.searchRecipeByScore(ingredientIdList, pageable, userId);
            resultMap.put("result", result);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "평점검색 에러");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cost")
    public ResponseEntity searchByCost(@RequestBody SearchRecipeDTO data, @RequestParam long userId, Pageable pageable){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<Long> ingredientIdList = new ArrayList<>();
            for (SearchRecipeByIngredientReqDTO dto : data.getIngredientList()) {
                ingredientIdList.add(dto.getIngredientId());
            }
            SearchRecipeResDTO result = searchService.searchRecipeByCost(ingredientIdList, pageable, userId);
            resultMap.put("result", result);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "평점검색 에러");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{keyWord}")
    public ResponseEntity searchByName(@RequestParam long userId, Pageable pageable, @PathVariable("keyWord") String keyWord ){
        Map<String, Object> resultMap = new HashMap<>();
        try{

            SearchRecipeResDTO result = searchService.searchRecipeByName(pageable, userId, keyWord);
            resultMap.put("result", result);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "레시피 이름검색에서 에러");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name")
    public ResponseEntity searchByNoName(@RequestParam long userId, Pageable pageable){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            SearchRecipeResDTO result = searchService.searchRecipeByNoName(pageable, userId);
            resultMap.put("result", result);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
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
