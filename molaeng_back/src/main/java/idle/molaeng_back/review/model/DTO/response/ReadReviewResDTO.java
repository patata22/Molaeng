package idle.molaeng_back.review.model.DTO.response;

import idle.molaeng_back.review.model.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReadReviewResDTO {
    long reviewId;
    String userNickname;
    int reviewScore;
    LocalDateTime reviewDate;
    String reviewContent;
    int likeCnt;
    boolean isLiked;
    long userId;

    public ReadReviewResDTO(long reviewId, String userNickname, int reviewScore, LocalDateTime reviewDate, String reviewContent, int likeCnt, boolean isLiked, long userId) {
        this.reviewId = reviewId;
        this.userNickname = userNickname;
        this.reviewScore = reviewScore;
        this.reviewDate = reviewDate;
        this.reviewContent = reviewContent;
        this.likeCnt = likeCnt;
        this.isLiked = isLiked;
        this.userId = userId;
    }
}
