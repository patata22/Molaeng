package idle.molaeng_back.recipe.controller;

import idle.molaeng_back.recipe.model.response.IngredientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {

    private static final Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @GetMapping("/recipe/ingredient/{recipeId}")
    public ResponseEntity<List<IngredientResponse.IngredientInfo> > getRecipeIngredients(@PathVariable Long recipeId) {
        return new ResponseEntity<>();
    }
}