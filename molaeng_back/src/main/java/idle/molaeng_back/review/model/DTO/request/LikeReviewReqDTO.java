package idle.molaeng_back.review.model.DTO.request;

import lombok.Getter;

@Getter
public class LikeReviewReqDTO {
    long userId;
    long reviewId;

    public LikeReviewReqDTO(long userId, long reviewId) {
        this.userId = userId;
        this.reviewId = reviewId;
    }
}
