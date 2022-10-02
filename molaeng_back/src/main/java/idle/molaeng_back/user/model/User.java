package idle.molaeng_back.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import idle.molaeng_back.diary.model.Diary;
import idle.molaeng_back.recipe.model.entity.RecipeLike;
import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.review.model.ReviewLike;
import idle.molaeng_back.user.model.DTO.UserProfileRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(name="nickname")
    private String nickname;

    @Column(name="uuid")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gugun_id")
    @JsonBackReference
    private Gugun gugun;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RecipeLike> recipeLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ReviewLike> reviewLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Diary> diaryList = new ArrayList<>();

    public void changeProfile(UserProfileRequest userProfileRequest, Gugun gugun){
        this.nickname = userProfileRequest.getNickname();
        this.gugun = gugun;
    }

    @Builder
    public User(long userId, String nickname, String uuid, Gugun gugun, List<RecipeLike> recipeLikeList, List<Review> reviewList, List<ReviewLike> reviewLikeList, List<Diary> diaryList) {
        this.userId = userId;
        this.nickname = nickname;
        this.uuid = uuid;
        this.gugun = gugun;
        this.recipeLikeList = recipeLikeList;
        this.reviewList = reviewList;
        this.reviewLikeList = reviewLikeList;
        this.diaryList = diaryList;
    }
}