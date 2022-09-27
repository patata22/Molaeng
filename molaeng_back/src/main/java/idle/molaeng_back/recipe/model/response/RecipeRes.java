package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeRes {
    long recipeId;
    String recipeName;
    int recipeKcal;
//    float avgScore;
    String recipeImg;
    boolean isLiked;

    public RecipeRes(long recipeId, String recipeName, int recipeKcal, String recipeImg, boolean isLiked) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeKcal = recipeKcal;
        this.recipeImg = recipeImg;
        this.isLiked = isLiked;
    }
}
