package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.DTO.request.CreateReviewReqDTO;
import idle.molaeng_back.review.model.DTO.response.ReadReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ScoreResDTO;
import idle.molaeng_back.review.model.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ReviewService {

    long createReview(Long recipeId, long userId, CreateReviewReqDTO createReviewDTO);
    void deleteReviewById(long reviewId);
    Review readReviewById(long reviewId);
    ReviewResDTO readReviewByUserId(long userId, Pageable pageable);

    void transUserReview(long userId);

    ReviewResDTO readReviewByRecipeId(long userId, long recipeId, Pageable pageable);
    ScoreResDTO findScoreByRecipeId(long recipeId);
    int countReviewByRecipeId(long recipeId);


    ReviewResDTO readReviewByLikeCount(long recipeId, long userId, Pageable pageable);
}
