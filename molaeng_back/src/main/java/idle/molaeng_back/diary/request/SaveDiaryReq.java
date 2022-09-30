package idle.molaeng_back.diary.request;

import lombok.Getter;

@Getter
public class SaveDiaryReq {
    private long userId;
    private long recipeId;
    private int saveCost;

    public SaveDiaryReq(long userId, long recipeId, int saveCost) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.saveCost = saveCost;
    }
}
