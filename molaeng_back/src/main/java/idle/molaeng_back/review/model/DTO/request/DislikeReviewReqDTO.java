package idle.molaeng_back.review.model.DTO.request;

import lombok.Getter;

@Getter
public class DislikeReviewReqDTO {
    long userId;
    long reviewId;

    public DislikeReviewReqDTO(long userId, long reviewId) {
        this.userId = userId;
        this.reviewId = reviewId;
    }
}
