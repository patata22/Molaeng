package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public Review readReviewById(long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

    @Override
    public List<Review> readReviewByUserId(long userId) {
        return reviewRepository.findByUserUserId(userId);
    }

    @Override
    public void deleteReviewById(long reviewId) {
        reviewRepository.deleteByReviewId(reviewId);
    }
}
