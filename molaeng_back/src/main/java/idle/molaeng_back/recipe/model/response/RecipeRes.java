package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;


@Builder
public class RecipeRes {
    long recipeId;
    String recipeName;
    int recipeKcal;
    String recipeImg;
    boolean isLiked;

    public RecipeRes(long recipeId, String recipeName, int recipeKcal, String recipeImg, boolean isLiked) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeKcal = recipeKcal;
        this.recipeImg = recipeImg;
        this.isLiked = isLiked;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getRecipeKcal() {
        return recipeKcal;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    @Override
    public String toString()
    {
        return "RecipeRes{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", recipeKcal=" + recipeKcal +
                ", recipeImg='" + recipeImg + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }
}
