package idle.molaeng_back.review.model.DTO.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateReviewReqDTO {
    Long userId;
    int score;
    String reviewContent;
    @Builder
    public CreateReviewReqDTO(Long userId, int score, String reviewContent) {
        this.userId = userId;
        this.score = score;
        this.reviewContent = reviewContent;
    }
}
