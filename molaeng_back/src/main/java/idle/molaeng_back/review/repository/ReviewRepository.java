package idle.molaeng_back.review.repository;

import idle.molaeng_back.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review save(Review review);
    Review findByReviewId(long reviewId);
    List<Review> findByUserUserId(long userId);
    List<Review> findByRecipeRecipeId(long recipeId);
    void deleteByReviewId(long reviewId);


}
