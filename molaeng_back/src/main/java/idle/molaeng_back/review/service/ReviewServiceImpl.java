package idle.molaeng_back.review.service;

import idle.molaeng_back.review.model.DTO.request.CreateReviewReqDTO;
import idle.molaeng_back.review.model.DTO.response.ReadReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.RecipeReviewResDTO;
import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.review.repository.ReviewLikeRepository;
import idle.molaeng_back.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{

    ////////// 회원 탈퇴 시에 기존 있던거 더미로 바꾸는 로직 만들어야됨!!!!!!!!!!

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
//    private final UserRepository userRepository;
//    private final RecipeRepository recipeRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewLikeRepository reviewLikeRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewLikeRepository = reviewLikeRepository;
//        this.userRepository = userRepository;
//        this.recipeRepository= recipeRepository;
    }

    @Override
    public long createReview(Long recipeId, CreateReviewReqDTO createReviewDTO) {
        int score = createReviewDTO.getScore();
        Review review = Review.builder()
                .reviewContent(createReviewDTO.getReviewContent())
                .reviewDate(LocalDateTime.now())
                .score(score)
//                .user(userRepository.findByUserId(createReviewDTO.getUserId()))
//                .recipe(recipeRepository.findByRecipeId)
                .build();
//        에러가 뜨는데 레포지토리 문제인지 아니면 다른 문제인지 나중에 확인해봐야겠다.
//        검색해본 결과 recipe 내에 함수 하나 만들어서 처리하는게 맞는듯
//        Recipe recipe = recipeRepository.findByRecipeId(recipeId);
//        if(score==1) recipe.getOneScore()++;
//        else if(score==2) recipe.getTwoScore()++;
//        else if(score==3) recipe.getThreeScore()++;
//        else if(score==4) recipe.getFourScore()++;
//        else if(score==5) recipe.getFiveScore()++;
        reviewRepository.save(review);
        return review.getReviewId();
    }

    @Override
    public Review readReviewById(long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

    @Override
    public List<ReadReviewResDTO> readReviewByUserId(long userId) {
        List<Review> reviewList = reviewRepository.findByUserUserId(userId);
        ArrayList<ReadReviewResDTO> readReviewResDTOS = new ArrayList<>();
        for (Review review : reviewList) {
            ReadReviewResDTO dto = reviewToReadReviewDTO(userId, review);
            readReviewResDTOS.add(dto);
        }
        return readReviewResDTOS;
    }

    @Override
    public void deleteReviewById(long reviewId) {
        reviewRepository.deleteByReviewId(reviewId);
    }

    @Override
    public void transUserReview(long userId) {
        int dummyId=0;
//        userRepository 생기면 그때 테스트
//        User user = userRepository.findOnebyUserUserId(userId);
//        User dummy = userRepository.findOnebyuserUserId(dummyId);
        List<Review> reviewList = reviewRepository.findByUserUserId(userId);
        for (Review review : reviewList) {
//            review.changeUser(dummy);
        }
    }

    @Override
    public RecipeReviewResDTO readReviewByRecipeId(int sort, int page, long userId, long recipeId) {

        List<Review> reviewList = null;
        switch(sort){
            case 0:
                reviewList = reviewRepository.findAllByRecipeRecipeIdOrderByScoreDesc(recipeId);
                break;
            case 1:
                reviewList = reviewRepository.findAllByRecipeRecipeIdOrderByScore(recipeId);
                break;
            case 2:
                reviewList = reviewRepository.findAllByRecipeRecipeIdOrderByReviewDateDesc(recipeId);
                break;
        }
        int size = reviewList.size();
        int[] scoreCnt= new int[5];
        ArrayList<ReadReviewResDTO> readReviewResDTOS = new ArrayList<>();
        for (Review review : reviewList) {
            ReadReviewResDTO dto = reviewToReadReviewDTO(userId, review);
            readReviewResDTOS.add(dto);
            scoreCnt[review.getScore()-1]+=1;
        }

        int total = 0;
        for(int i=0;i<5;i++){
            total += scoreCnt[i]*(i+1);
        }
        //이거 맞는지 모르겠는데
        float avgScore = Math.round((float)(total*10/size)/10);
        return new RecipeReviewResDTO(avgScore, scoreCnt, readReviewResDTOS);

    }



    //따로 추출한 메서드
    private ReadReviewResDTO reviewToReadReviewDTO(long userId, Review review) {
        long reviewId = review.getReviewId();
        boolean isLiked= reviewLikeRepository.countByUserUserIdAndReviewReviewId(userId, reviewId)==0?false:true;
        ReadReviewResDTO dto = ReadReviewResDTO.builder()
                .reviewId(reviewId)
                .reviewScore(review.getScore())
//                    .userNickname(userRepository.getUserNickname())
                .reviewDate(review.getReviewDate())
                .reviewContent(review.getReviewContent())
                .likeCnt(reviewLikeRepository.countByReviewReviewId(reviewId))
                .isLiked(isLiked)
                .build();
        return dto;
    }

}
