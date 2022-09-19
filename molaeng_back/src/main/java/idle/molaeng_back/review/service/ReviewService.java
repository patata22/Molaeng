package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.DTO.request.CreateReviewReqDTO;
import idle.molaeng_back.review.model.DTO.response.ReadReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.RecipeReviewResDTO;
import idle.molaeng_back.review.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    long createReview(Long recipeId, CreateReviewReqDTO createReviewDTO);
    Review readReviewById(long reviewId);
    List<ReadReviewResDTO> readReviewByUserId(long userId);
    RecipeReviewResDTO readReviewByRecipeId(int sort, int page, long userId, long recipeId);
    void deleteReviewById(long reviewId);

    void transUserReview(long userId);
}
