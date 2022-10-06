package idle.molaeng_back.recipe.controller;

import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import idle.molaeng_back.recipe.model.response.RecipeLikeResponse;
import idle.molaeng_back.recipe.service.RecipeLikeServiceImpl;
import idle.molaeng_back.search.DTO.response.RecipeResDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe/like")
public class RecipeLikeController {
    private static final Logger logger = LoggerFactory.getLogger(RecipeLikeController.class);

    private final RecipeLikeServiceImpl recipeLikeService;
    @ApiOperation(value="찜한 레시피 목록 불러오기", notes = "user_id를 이용하여 사용자가 찜한 레시피의 전체 목록을 불러온다.")
    @GetMapping
    public ResponseEntity getRecipeLikeList(@RequestHeader Map<String,Object> header){
        long userId = Long.parseLong((String)header.get("userid"));

        HashMap<String, Object> result = new HashMap<>();


        try {
            List<RecipeResDTO> responseList = recipeLikeService.getRecipeLikeList(userId);
            for(int i = 0; i < responseList.size(); i++){
                logger.info(responseList.get(i).getRecipeName());
            }
            result.put("result", responseList);
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.put("result", userId);
            result.put("message", "찜한 레시피 목록 불러오기 실패");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value="레시피 찜하기", notes = "recipe_id와 user_id를 이용하여 찜할 레시피를 등록한다.")
    @PostMapping
    public ResponseEntity registRecipeLike(@RequestHeader Map<String,Object> header, @RequestBody RecipeLikeRequest recipeLikeRequest) {
        long userId = Long.parseLong((String)header.get("userid"));

        HashMap<String, Object> result = new HashMap<>();
        try {
            RecipeLikeResponse recipeLikeResponse = recipeLikeService.registRecipeLike(userId, recipeLikeRequest.getRecipeId());
            result.put("result", recipeLikeResponse);
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.put("result", recipeLikeRequest.getRecipeId());
            result.put("message", "레시피 찜 기능 오류");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value="레시피 찜하기 해제", notes = "recipe_id와 user_id를 이용하여 찜한 레시피를 해제한다.")
    @DeleteMapping
    public ResponseEntity deleteRecipeLike(@RequestHeader Map<String,Object> header, @RequestBody RecipeLikeRequest recipeLikeRequest) {
//        System.out.println(recipeLikeRequest.getRecipeId());
        long userId = Long.parseLong((String)header.get("userid"));

        HashMap<String, Object> result = new HashMap<>();
        try {
            RecipeLikeResponse recipeLikeResponse = recipeLikeService.deleteRecipeLike(userId, recipeLikeRequest.getRecipeId());
            result.put("result", recipeLikeResponse);
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.put("result", recipeLikeRequest.getRecipeId());
            result.put("message", "레시피 찜 해제 오류");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
