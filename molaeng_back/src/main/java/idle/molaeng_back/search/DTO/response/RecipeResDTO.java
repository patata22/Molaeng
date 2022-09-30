package idle.molaeng_back.search.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecipeResDTO {
    long recipeId;
    String recipeName;
    List<IngredientResDTO> ingredientList;
    String recipeImage;
    int recipeKcal;
    int isLiked;
    double avgScore;
    //int cost;

    @Builder
    public RecipeResDTO(long recipeId, String recipeName, List<IngredientResDTO> ingredientList, String recipeImage, int recipeKcal, int isLiked, double avgScore) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.ingredientList = ingredientList;
        this.recipeImage = recipeImage;
        this.recipeKcal = recipeKcal;
        this.isLiked = isLiked;
        this.avgScore = avgScore;
    }
}
