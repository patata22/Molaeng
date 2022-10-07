package idle.molaeng_back.review.model.DTO.response;

import lombok.Getter;

@Getter
public class ScoreResDTO {
    int[] scoreCnt;
    float avgScore;

    public ScoreResDTO(int[] scoreCnt, float avgScore) {
        this.scoreCnt = scoreCnt;
        this.avgScore = avgScore;
    }
}
