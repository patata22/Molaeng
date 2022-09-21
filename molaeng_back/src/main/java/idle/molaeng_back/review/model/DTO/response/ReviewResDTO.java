package idle.molaeng_back.review.model.DTO.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ReviewResDTO {
    int page;
    boolean hasNext;
    List<ReadReviewResDTO> reviewList;

    public ReviewResDTO(int page, boolean hasNext, List<ReadReviewResDTO> reviewList) {
        this.page = page;
        this.hasNext = hasNext;
        this.reviewList = reviewList;
    }
}
