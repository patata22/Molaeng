package idle.molaeng_back.review.model.DTO.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateReviewReqDTO {
    int score;
    String reviewContent;
    @Builder
    public CreateReviewReqDTO(int score, String reviewContent) {
        this.score = score;
        this.reviewContent = reviewContent;
    }
}
