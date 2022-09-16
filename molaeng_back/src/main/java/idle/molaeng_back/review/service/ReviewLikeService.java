package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.DTO.response.LikeReviewResDTO;
import idle.molaeng_back.review.model.ReviewLike;

import java.util.Map;

public interface ReviewLikeService {

    public LikeReviewResDTO like(long userId, long reviewId);
    public Map<String, Long> dislike(long userId, long reviewId);
}
