package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeListResponse {
    private long recipeId;
    private String recipeName;
    private int recipeKcal;
    private int needPrice;
    private double avgScore;
    private String recipeImg;
    private boolean isliked;

    @Builder
    public RecipeListResponse(long recipeId, String recipeName, int recipeKcal, int needPrice, double avgScore, String recipeImg, boolean isliked) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeKcal = recipeKcal;
        this.needPrice = needPrice;
        this.avgScore = avgScore;
        this.recipeImg = recipeImg;
        this.isliked = isliked;
    }
}
