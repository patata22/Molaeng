package idle.molaeng_back.recipe.controller;

import idle.molaeng_back.diary.controller.DiaryController;
import idle.molaeng_back.recipe.model.response.RecipeDetailRes;
import idle.molaeng_back.recipe.model.response.RecipeIngredientRes;
import idle.molaeng_back.recipe.model.response.RecipeMainSubIngredientRes;
import idle.molaeng_back.recipe.model.response.RecipeRes;
import idle.molaeng_back.recipe.service.RecipeDetailService;
import idle.molaeng_back.recipe.service.RecipeIngredientService;
import idle.molaeng_back.recipe.service.RecipeService;
import idle.molaeng_back.recipe.service.RecipeSubIngredientService;
import idle.molaeng_back.review.model.DTO.response.ReadReviewResDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    //service 가져오기
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeDetailService recipeDetailService;
    @Autowired
    RecipeIngredientService recipeIngredientService;
    @Autowired
    RecipeSubIngredientService recipeSubIngredientService;

    //레시피 정보 조회(상단부분)
    @GetMapping("/{recipeId}")
    public ResponseEntity getRecipeInfo(@PathVariable long recipeId){

        Map<String, Object> resultMap = new HashMap<>();

        try{
            //userId의 경우 JWT 토큰을 이용해 가져와야 함
            long userId = 1;

            RecipeRes result = recipeService.readRecipeById(userId, recipeId);
            System.out.println(result);

            resultMap.put("result", result);
            resultMap.put("message", "success");



            logger.info(result+"");

            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "레시피 정보 조회 오류");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    //레시피 조리방법 조회
    @GetMapping("/detail/{recipeId}")
    public ResponseEntity getRecipeDetail(@PathVariable long recipeId){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<RecipeDetailRes> result = recipeDetailService.readRecipeDetailByRecipeId(recipeId);

            resultMap.put("result", result);
            resultMap.put("message", "success");


            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "레시피 조리방법 조회 오류");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    //레시피 재료 조회
    @GetMapping("/allingredient/{recipeId}")
    public ResponseEntity getRecipeIngredient(@PathVariable long recipeId){
        Map<String, Object> resultMap = new HashMap<>();
        try{
//            List<RecipeIngredientRes> result = recipeIngredientService.readRecipeIngredientByRecipeId(recipeId);
            RecipeMainSubIngredientRes result = RecipeMainSubIngredientRes.builder()
                            .recipeIngredientResList(recipeIngredientService.readRecipeIngredientByRecipeId(recipeId))
                                    .recipeSubIngredientResList(recipeSubIngredientService.readRecipeSubIngredientByRecipeId(recipeId))
                                            .build();

            resultMap.put("result", result);
            resultMap.put("message", "success");

            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "레시피 재료 조회 오류");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }
}
