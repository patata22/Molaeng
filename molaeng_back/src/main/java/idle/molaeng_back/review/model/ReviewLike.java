package idle.molaeng_back.review.model;

import idle.molaeng_back.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_like_id")
    private long reviewLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public ReviewLike(long reviewLikeId, Review review, User user) {
        this.reviewLikeId = reviewLikeId;
        this.review = review;
        this.user = user;
    }
}
