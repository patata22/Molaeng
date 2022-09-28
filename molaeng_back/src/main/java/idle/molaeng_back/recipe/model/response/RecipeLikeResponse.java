package idle.molaeng_back.recipe.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeLikeResponse {
    private long recipeLikeId;

    @Builder
    public RecipeLikeResponse(long recipeLikeId){
        this.recipeLikeId = recipeLikeId;
    }

}
