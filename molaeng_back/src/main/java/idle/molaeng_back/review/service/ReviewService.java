package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.DTO.request.CreateReviewReqDTO;
import idle.molaeng_back.review.model.DTO.response.ReadReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ScoreResDTO;
import idle.molaeng_back.review.model.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ReviewService {

    long createReview(Long recipeId, CreateReviewReqDTO createReviewDTO);
    Review readReviewById(long reviewId);
    ReviewResDTO readReviewByUserId(long userId, Pageable pageable);

    ReviewResDTO readReviewByRecipeId(long userId, long recipeId, Pageable pageable);


    ScoreResDTO findScoreByRecipeId(long recipeId);

    void deleteReviewById(long reviewId);


    void transUserReview(long userId);
}
