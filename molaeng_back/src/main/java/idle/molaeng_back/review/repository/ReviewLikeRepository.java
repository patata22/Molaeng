package idle.molaeng_back.review.repository;

import idle.molaeng_back.review.model.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    ReviewLike save(ReviewLike reviewLike);

    void deleteByUserUserIdAndReviewReviewId(long userId, long reviewId);
    int countByUserUserIdAndReviewReviewId(long userId, long reviewId);
}
