package idle.molaeng_back.review.model.DTO.response;

import lombok.Getter;

@Getter
public class LikeReviewResDTO {
    long reviewLikeId;
    long reviewId;
    long reviewLikeCnt;

    public LikeReviewResDTO(long reviewLikeId, long reviewId, long reviewLikeCnt) {
        this.reviewLikeId = reviewLikeId;
        this.reviewId = reviewId;
        this.reviewLikeCnt = reviewLikeCnt;
    }
}
