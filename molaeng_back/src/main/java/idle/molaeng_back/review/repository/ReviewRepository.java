package idle.molaeng_back.review.repository;

import idle.molaeng_back.review.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review save(Review review);
    Review findByReviewId(long reviewId);
    List<Review> findByUserUserId(long userId);
    Slice<Review> findAllByUserUserId(long userId, Pageable pageable);

    List<Review> findAllByUserUserId(long userId);
    void deleteByReviewId(long reviewId);

    Slice<Review> findAllByRecipeRecipeId(long recipeId, Pageable pageable);
    // 리뷰 총 숫자
    int countByRecipeRecipeId(long recipeId);

    List<Review> findAllByRecipeRecipeId(long recipeId);




}
