package idle.molaeng_back.user.model.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeLikeRequest {
    private long userId;
    private long recipeId;

    @Builder
    public RecipeLikeRequest(long userId, long recipeId){
        this.userId = userId;
        this.recipeId = recipeId;
    }
}
