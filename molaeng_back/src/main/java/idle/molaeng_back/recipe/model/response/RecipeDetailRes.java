package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeDetailRes {
    long recipeDetailId;
    int recipeOrder;
    String recipeContent;
    String recipeDetailImg;

    public RecipeDetailRes(long recipeDetailId, int recipeOrder, String recipeContent, String recipeDetailImg) {
        this.recipeDetailId = recipeDetailId;
        this.recipeOrder = recipeOrder;
        this.recipeContent = recipeContent;
        this.recipeDetailImg = recipeDetailImg;
    }
}
