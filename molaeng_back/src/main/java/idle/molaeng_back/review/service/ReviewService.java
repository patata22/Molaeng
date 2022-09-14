package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    public void saveReview(Review review);
    public Review readReviewById(long reviewId);
    public List<Review> readReviewByUserId(long userId);
    public void deleteReviewById(long reviewId);

}
