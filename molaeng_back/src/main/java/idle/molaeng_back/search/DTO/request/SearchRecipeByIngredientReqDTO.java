package idle.molaeng_back.search.DTO.request;

import lombok.Getter;

@Getter
public class SearchRecipeByIngredientReqDTO {
    String recipeName;
    long recipeId;

    public SearchRecipeByIngredientReqDTO(String recipeName, long recipeId) {
        this.recipeName = recipeName;
        this.recipeId = recipeId;
    }
}
