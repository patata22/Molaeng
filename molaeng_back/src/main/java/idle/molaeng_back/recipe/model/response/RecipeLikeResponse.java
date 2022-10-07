package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeLikeResponse {
    private long recipeId;

    @Builder
    public RecipeLikeResponse(long recipeId){
        this.recipeId = recipeId;
    }

}
