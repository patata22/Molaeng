package idle.molaeng_back.review.controller;

import idle.molaeng_back.review.model.DTO.request.*;
import idle.molaeng_back.review.model.DTO.response.LikeReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ReadReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.RecipeReviewResDTO;
import idle.molaeng_back.review.service.ReviewLikeService;
import idle.molaeng_back.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private final ReviewLikeService reviewLikeService;
    @Autowired
    private final ReviewService reviewService;



    @GetMapping
    public ResponseEntity readMyReview(@RequestBody long userId){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            List<ReadReviewResDTO> result = reviewService.readReviewByUserId(userId);
            resultMap.put("result", result);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "내 리뷰 보기 조회 오류");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{recipeId}")
    // 페이징 적용해야하는데 일단은 다 던져주는걸로
    public ResponseEntity readReview(@PathVariable long recipeId, @RequestBody RecipeReviewReqDTO request) {
        Map<String, Object> resultMap = new HashMap<>();
        try{
            int sort=request.getSort();
            int page=request.getPage();
            long userId = request.getUserId();
            RecipeReviewResDTO result = reviewService.readReviewByRecipeId(sort,page,userId,recipeId);
            resultMap.put("message", "success");
            //나중엔 DTO로 바꾸자
            resultMap.put("result", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "레시피 리뷰조회에서 에러났다아아");
//            resultMap.put("error", e.getStackTrace());
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{recipeId}")
    public ResponseEntity createReview(@PathVariable long recipeId, @RequestBody CreateReviewReqDTO createReviewDTO){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            long result = reviewService.createReview(recipeId, createReviewDTO);
            resultMap.put("message", "success");
            resultMap.put("reviewId", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "리뷰 등록에서 에러났다아아");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/recipe")
    public ResponseEntity deleteReview(@RequestBody DeleteReviewReqDTO deleteReviewDTO){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            long reviewId = deleteReviewDTO.getReviewId();
            //리뷰에 있는 좋아요도 같이 날리는 로직 추가되야함 - Cascade 설정했으니 되는지 테스트해볼것, 안되면 따로 로직 구현
            reviewService.deleteReviewById(reviewId);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "리뷰 등록에서 에러났다아아");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/like")
    public ResponseEntity likeReview(@RequestBody LikeReviewReqDTO likeReviewDTO){
        long userId = likeReviewDTO.getUserId();
        long reviewId = likeReviewDTO.getReviewId();
        Map<String, Object> resultMap = new HashMap<>();
        try{
            LikeReviewResDTO result = reviewLikeService.like(userId, reviewId);
            resultMap.put("message", "success");
            resultMap.put("result", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "리뷰 좋아요 등록에서 에러 남");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/like")
    public ResponseEntity dislikeReview(@RequestBody DislikeReviewReqDTO request){
        long userId = request.getUserId();
        long reviewId = request.getReviewId();
        Map<String, Object> resultMap = new HashMap<>();
        try{
            Map result = reviewLikeService.dislike(userId, reviewId);
            resultMap.put("result", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "리뷰 좋아요 취소에서 에러남");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }
}