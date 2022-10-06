package idle.molaeng_back.recipe.model.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeLikeRequest {
//    private long userId;
    private long recipeId;

    @Builder
    public RecipeLikeRequest(long recipeId){
        this.recipeId = recipeId;
    }
}
