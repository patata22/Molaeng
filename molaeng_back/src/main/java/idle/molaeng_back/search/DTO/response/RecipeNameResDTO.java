package idle.molaeng_back.search.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecipeNameResDTO {
    long recipeId;
    String recipeName;

    @Builder
    public RecipeNameResDTO(long recipeId, String recipeName) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
    }
}
