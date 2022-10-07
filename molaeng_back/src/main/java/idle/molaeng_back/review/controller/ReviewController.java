package idle.molaeng_back.review.controller;

import idle.molaeng_back.review.model.DTO.request.*;
import idle.molaeng_back.review.model.DTO.response.LikeReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ReviewResDTO;
import idle.molaeng_back.review.model.DTO.response.ScoreResDTO;
import idle.molaeng_back.review.service.ReviewLikeService;
import idle.molaeng_back.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewLikeService reviewLikeService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity readMyReview(@CookieValue(name="userId") Long userId, Pageable pageable){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            ReviewResDTO result = reviewService.readReviewByUserId(userId, pageable);
            resultMap.put("result", result);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "내 리뷰 보기 조회 오류");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity readReview(@PathVariable long recipeId, Pageable pageable, @CookieValue(name="userId", required = false) Long userId){
        Map<String, Object> resultMap = new HashMap<>();
        if(userId==null) userId=0L;
        try {
            ReviewResDTO result = reviewService.readReviewByRecipeId(userId, recipeId, pageable);
            resultMap.put("message", "success");
            resultMap.put("result", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }
        catch(Exception e){
            resultMap.put("message", "레시피 리뷰조회에서 에러났다아아");
            e.printStackTrace();
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/count/{recipeId}")
    public ResponseEntity countReview(@PathVariable long recipeId){
        Map<String, Object> resultMap= new HashMap<>();
        try{
            int result = reviewService.countReviewByRecipeId(recipeId);
            resultMap.put("message", "success");
            resultMap.put("result", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "리뷰수 조회에서 에러났네???????");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/score/{recipeId}")
    public ResponseEntity getScore(@PathVariable long recipeId){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            ScoreResDTO result = reviewService.findScoreByRecipeId(recipeId);
            resultMap.put("message", "success");
            resultMap.put("result", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch (Exception e){
            resultMap.put("message", "점수조회에서 에러ㅓㅓㅓㅓㅓㅓㅓㅓ");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{recipeId}")
    public ResponseEntity createReview(@PathVariable long recipeId, @CookieValue(name="userId", required = false) Long userId, @RequestBody CreateReviewReqDTO createReviewDTO){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            long result = reviewService.createReview(recipeId, userId, createReviewDTO);
            resultMap.put("message", "success");
            resultMap.put("reviewId", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "리뷰 등록에서 에러났다아아");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteReview(@RequestBody DeleteReviewReqDTO deleteReviewDTO){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            long reviewId = deleteReviewDTO.getReviewId();
            //리뷰에 있는 좋아요도 같이 날리는 로직 추가되야함 - Cascade 설정했으니 되는지 테스트해볼것, 안되면 따로 로직 구현
            reviewService.deleteReviewById(reviewId);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "리뷰 삭제에서 에러났다아아");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/like")
    public ResponseEntity likeReview(@RequestBody LikeReviewReqDTO likeReviewDTO){
        long reviewId = likeReviewDTO.getReviewId();
        long userId = likeReviewDTO.getUserId();
        Map<String, Object> resultMap = new HashMap<>();
        try{
            LikeReviewResDTO result = reviewLikeService.like(userId, reviewId);
            resultMap.put("message", "success");
            resultMap.put("result", result);
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "리뷰 좋아요 등록에서 에러 남");
            e.printStackTrace();
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
            e.printStackTrace();
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/like/{recipeId}")
    public ResponseEntity readReviewByLikeCount(@PathVariable long recipeId,@CookieValue(name="userId", required = false) Long userId, Pageable pageable){
        if(userId==null) userId=0L;
        Map<String, Object> resultMap = new HashMap<>();
        try{
            ReviewResDTO result = reviewService.readReviewByLikeCount(recipeId, userId, pageable);
            resultMap.put("result", result);
            resultMap.put("message", "success");
            return new ResponseEntity(resultMap, HttpStatus.OK);
        }catch(Exception e){
            resultMap.put("message", "좋아요순 검색에서 에러!");
            return new ResponseEntity(resultMap, HttpStatus.BAD_REQUEST);
        }

    }
}