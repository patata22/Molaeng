package idle.molaeng_back.recipe.controller;

import idle.molaeng_back.recipe.model.request.RecipeLikeRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipe/like")
public class RecipeLikeController {

    @ApiOperation(value="레시피 찜하기", notes = "recipe_id와 user_id를 이용하여 찜할 레시피를 등록한다.")
    @PostMapping
    public ResponseEntity registRecipeLike(@RequestBody RecipeLikeRequest recipeLikeRequest) {
        return null;
    }

    @ApiOperation(value="레시피 찜하기 해제", notes = "recipe_id와 user_id를 이용하여 찜한 레시피를 해제한다.")
    @DeleteMapping
    public ResponseEntity deleteRecipeLike(@RequestBody RecipeLikeRequest recipeLikeRequest) {
        return null;
    }

}
