package idle.molaeng_back.diary.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetDiaryRes {
    private long diaryId;
    private long recipeId;
    private String recipeName;
    private int saveCost;

    public GetDiaryRes(long diaryId, long recipeId, String recipeName, int saveCost) {
        this.diaryId = diaryId;
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.saveCost = saveCost;
    }
}
