package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.ReviewLike;
import idle.molaeng_back.review.repository.ReviewLikeRepository;
import idle.molaeng_back.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewLikeServiceImpl implements ReviewLikeService{

    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewLikeServiceImpl(ReviewLikeRepository reviewLikeRepository, ReviewRepository reviewRepository) {
        this.reviewLikeRepository = reviewLikeRepository;
        this.reviewRepository= reviewRepository;
    }

    @Override
    public void like(long userId, long reviewId) {
        reviewLikeRepository.save(new ReviewLike());
        ReviewLike.builder().user("bula").review(reviewRepository.findByReviewId(reviewId)).build();
    }

    @Override
    public void dislike(long userId, long reviewId) {
        reviewLikeRepository.deleteByUserUserIdAndReviewReviewId(userId, reviewId);
    }
}
