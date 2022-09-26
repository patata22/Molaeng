package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.DTO.response.LikeReviewResDTO;
import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.review.model.ReviewLike;
import idle.molaeng_back.review.repository.ReviewLikeRepository;
import idle.molaeng_back.review.repository.ReviewRepository;
import idle.molaeng_back.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ReviewLikeServiceImpl implements ReviewLikeService{
//    private final UserRepository userRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewLikeServiceImpl(ReviewLikeRepository reviewLikeRepository, ReviewRepository reviewRepository) {
        this.reviewLikeRepository = reviewLikeRepository;
        this.reviewRepository= reviewRepository;
    }

    @Override
    public LikeReviewResDTO like(long userId, long reviewId) {
        User temp = User.builder().userId(1L).build();
        //userReposiotry까지 땡겨와야함
        // User user = userRepository.findById(userId)
        Review review = reviewRepository.findByReviewId(reviewId);
        //이 줄 까먹지 말고 수정(temp 있는거 다)
        ReviewLike reviewLike = ReviewLike.builder().user(temp).review(review).build();
        reviewLikeRepository.save(reviewLike);
        return new LikeReviewResDTO(reviewLike.getReviewLikeId(), review.getReviewId(),temp.getUserId());

    }

    @Override
    public Map<String, Long> dislike(long userId, long reviewId) {

        //존재하는 좋아요인지에 대한 밸리데이션 추가 해야하나?
        ReviewLike reviewLike = reviewLikeRepository.findOneByUserUserIdAndReviewReviewId(userId, reviewId);
        long reviewLikeId = reviewLike.getReviewLikeId();
        reviewLikeRepository.deleteByUserUserIdAndReviewReviewId(userId, reviewId);
        Map<String, Long> result = new HashMap<>();
        result.put("reviewLikeId", reviewLikeId);
        result.put("userId", userId);
        result.put("reviewId", reviewId);
        return result;
    }
}
