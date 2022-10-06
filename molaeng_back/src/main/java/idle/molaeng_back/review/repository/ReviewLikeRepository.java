package idle.molaeng_back.review.repository;

import idle.molaeng_back.review.model.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    ReviewLike save(ReviewLike reviewLike);
    ReviewLike findAllByUserUserIdAndReviewReviewId(long userId, long reviewId);
    void deleteByUserUserIdAndReviewReviewId(long userId, long reviewId);

    //요건 쓸일이 없을지도?
    int countByUserUserIdAndReviewReviewId(long userId, long reviewId);
    int countByReviewReviewId(long reviewID);
}
