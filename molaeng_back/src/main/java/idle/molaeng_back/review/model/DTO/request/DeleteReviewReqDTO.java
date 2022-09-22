package idle.molaeng_back.review.model.DTO.request;

import lombok.Getter;

@Getter
public class DeleteReviewReqDTO {
    long userId;
    long reviewId;

    public DeleteReviewReqDTO(long userId, long reviewId) {
        this.userId = userId;
        this.reviewId = reviewId;
    }
}
