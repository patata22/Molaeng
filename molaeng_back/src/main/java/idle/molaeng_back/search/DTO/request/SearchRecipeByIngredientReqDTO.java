package idle.molaeng_back.search.DTO.request;

import lombok.Getter;

@Getter
public class SearchRecipeByIngredientReqDTO {
    long ingredientId;
    String ingredientName;

    public SearchRecipeByIngredientReqDTO() {
    }

    public SearchRecipeByIngredientReqDTO(String ingredientName, long ingredientId) {
        this.ingredientName = ingredientName;
        this.ingredientId = ingredientId;
    }

}
