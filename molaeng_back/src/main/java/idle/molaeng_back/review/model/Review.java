package idle.molaeng_back.review.model;

import idle.molaeng_back.recipe.model.entity.Recipe;
import idle.molaeng_back.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private long reviewId;

    @Column(name="review_date")
    private LocalDateTime reviewDate;

    @Column(name="score")
    private int score;

    @Column(name="review_content")
    private String reviewContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReviewLike> reviewLikeList;
    @Builder
    public Review(long reviewId, LocalDateTime reviewDate, int score, String reviewContent, User user, Recipe recipe, List<ReviewLike> reviewLikeList) {
        this.reviewId = reviewId;
        this.reviewDate = LocalDateTime.now();
        this.score = score;
        this.reviewContent = reviewContent;
        this.user = user;
        this.recipe = recipe;
        this.reviewLikeList = reviewLikeList;
    }
}
