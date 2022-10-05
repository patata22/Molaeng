package idle.molaeng_back.diary.request;

import lombok.Getter;

@Getter
public class SaveDiaryReq {
    private long recipeId;
    private int saveCost;

    public SaveDiaryReq(long recipeId, int saveCost) {
        this.recipeId = recipeId;
        this.saveCost = saveCost;
    }
}
