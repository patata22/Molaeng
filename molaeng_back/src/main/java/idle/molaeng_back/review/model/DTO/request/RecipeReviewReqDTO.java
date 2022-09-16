package idle.molaeng_back.review.model.DTO.request;

import lombok.Getter;

@Getter
public class RecipeReviewReqDTO {
    int sort;
    int page;
    long userId;

    public RecipeReviewReqDTO(int sort, int page, long userId) {
        this.sort = sort;
        this.page = page;
        this.userId = userId;
    }
}
