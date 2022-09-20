package idle.molaeng_back.review.model.DTO.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecipeReviewResDTO {
    float avgScore;
    int[] scoreCnt;
    List<ReadReviewResDTO> reviewList;

    public RecipeReviewResDTO(float avgScore, int[] scoreCnt, List<ReadReviewResDTO> reviewList) {
        this.avgScore = avgScore;
        this.scoreCnt = scoreCnt;
        this.reviewList = reviewList;
    }
}
