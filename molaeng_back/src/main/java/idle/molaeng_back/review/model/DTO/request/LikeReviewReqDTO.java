package idle.molaeng_back.review.model.DTO.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeReviewReqDTO {
    long reviewId;
    long userId;

    public LikeReviewReqDTO() {
    }

    public LikeReviewReqDTO(long reviewId, long userId) {
        this.reviewId = reviewId;
        this.userId = userId;
    }
}
