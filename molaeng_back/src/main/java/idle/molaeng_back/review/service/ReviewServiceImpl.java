package idle.molaeng_back.review.service;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.recipe.model.repository.RecipeRepository;
import idle.molaeng_back.review.model.DTO.request.CreateReviewReqDTO;
import idle.molaeng_back.review.model.DTO.response.ReadReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ScoreResDTO;
import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.review.repository.ReviewLikeRepository;
import idle.molaeng_back.review.repository.ReviewRepository;
import idle.molaeng_back.user.model.User;
import idle.molaeng_back.user.model.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewLikeRepository reviewLikeRepository, UserRepository userRepository, RecipeRepository recipeRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewLikeRepository = reviewLikeRepository;
        this.userRepository = userRepository;
        this.recipeRepository= recipeRepository;
    }

    @Override
    @Transactional
    public long createReview(Long recipeId, long userId,CreateReviewReqDTO createReviewDTO) {
        int score = createReviewDTO.getScore();
        User user = userRepository.findByUserId(userId);
        Recipe tempRecipe = Recipe.builder().recipeId(1).build();
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);
        switch(score){
            case 1:
                recipe.setOneScore(recipe.getOneScore()+1);
                break;
            case 2:
                recipe.setTwoScore(recipe.getTwoScore()+1);
                break;
            case 3:
                recipe.setThreeScore(recipe.getThreeScore()+1);
                break;
            case 4:
                recipe.setFourScore(recipe.getFourScore()+1);
                break;
            case 5:
                recipe.setFiveScore(recipe.getFiveScore()+1);
                break;
        }
        Review review = Review.builder()
                .reviewContent(createReviewDTO.getReviewContent())
                .reviewDate(LocalDateTime.now())
                .recipe(recipe)
                .user(user)
                .score(score)
                .build();
        reviewRepository.save(review);
        return review.getReviewId();
    }

    @Override
    public Review readReviewById(long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

    @Override
    public ReviewResDTO readReviewByUserId(long userId, Pageable pageable) {
        Slice<Review> reviewSlice = reviewRepository.findAllByUserUserId(userId, pageable);
        List<ReadReviewResDTO> reviewList = reviewSlice.getContent()
                .stream().map(x -> reviewToReadReviewDTO(userId, x))
                .collect(Collectors.toList());
        return new ReviewResDTO(reviewSlice.getNumber(), reviewSlice.hasNext(), reviewList);
    }

    @Override
    public void deleteReviewById(long reviewId) {
        reviewRepository.deleteByReviewId(reviewId);
    }

    @Override
    public void transUserReview(long userId) {
        int dummyId=0;
        User user = userRepository.findByUserId(userId);
        List<Review> reviewList = reviewRepository.findByUserUserId(userId);
        for (Review review : reviewList) {
//            review.changeUser(dummy);
        }
    }

    @Override
    public ReviewResDTO readReviewByRecipeId(long userId, long recipeId, Pageable pageable) {
        Slice<Review> pageReviewList = reviewRepository.findAllByRecipeRecipeId(recipeId, pageable);
        List<ReadReviewResDTO> reviewList = pageReviewList
                .getContent().stream()
                .map(x -> reviewToReadReviewDTO(userId, x))
                .collect(Collectors.toList());
        return new ReviewResDTO(pageReviewList.getNumber(), pageReviewList.hasNext(), reviewList);
    }

    @Override
    public ScoreResDTO findScoreByRecipeId(long recipeId) {
        int[] scoreCnt= new int[5];
        int total = 0;
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);
        scoreCnt[0] = recipe.getOneScore();
        scoreCnt[1] = recipe.getTwoScore();
        scoreCnt[2] = recipe.getThreeScore();
        scoreCnt[3] = recipe.getFourScore();
        scoreCnt[4] = recipe.getFiveScore();
        int size=0;
        for(int i=0;i<5;i++){
            total += scoreCnt[i]*(i+1);
            size += scoreCnt[i];
        }
        if(size==0){
            return new ScoreResDTO(scoreCnt, 0);
        }else{
            float avgScore = (float) (Math.round(((float)total*10/size))/10.0);
            return new ScoreResDTO(scoreCnt, avgScore);
        }

    }
    //userId 더미 수정할것!
    @Override
    public ReviewResDTO readReviewByLikeCount(long recipeId, long userId, Pageable pageable) {
        List<Review> tempList = reviewRepository.findAllByRecipeRecipeId(recipeId);
        long start = pageable.getOffset();
        long end= Math.min(start+pageable.getPageSize(), tempList.size());
        boolean hasNext = end < tempList.size();
        tempList.sort((o1,o2) -> o2.getReviewLikeList().size()-o1.getReviewLikeList().size());
        List<Review> subList = tempList.subList((int) start, (int) end);
        //여기 더미값!!
        List<ReadReviewResDTO> dtoList = subList.stream()
                .map(x -> reviewToReadReviewDTO(userId, x))
                .collect(Collectors.toList());
        return new ReviewResDTO(pageable.getPageNumber(), hasNext, dtoList);
    }

    @Override
    public int countReviewByRecipeId(long recipeId) {
        return reviewRepository.countByRecipeRecipeId(recipeId);
    }

    //따로 추출한 메서드
    private ReadReviewResDTO reviewToReadReviewDTO(long userId, Review review) {
        long reviewId = review.getReviewId();
        boolean isLiked= (reviewLikeRepository.countByUserUserIdAndReviewReviewId(userId, reviewId)==0?false:true);
        ReadReviewResDTO dto = ReadReviewResDTO.builder()
                .reviewId(reviewId)
                .reviewScore(review.getScore())
                .userNickname(review.getUser().getNickname())
                .reviewDate(review.getReviewDate())
                .reviewContent(review.getReviewContent())
                .likeCnt(reviewLikeRepository.countByReviewReviewId(reviewId))
                .isLiked(isLiked)
                .userId(review.getUser().getUserId())
                .build();
        return dto;
    }
}
