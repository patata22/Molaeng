package idle.molaeng_back.user.model;

import idle.molaeng_back.diary.model.Diary;
import idle.molaeng_back.recipe.model.entity.RecipeLike;
import idle.molaeng_back.review.model.Review;
import idle.molaeng_back.review.model.ReviewLike;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="nickname")
    private String nickname;

    @Column(name="uuid")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gugun_id")
    private Gugun gugun;

    @OneToMany(mappedBy = "user")
    private List<RecipeLike> recipeLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReviewLike> reviewLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Diary> diaryList = new ArrayList<>();

    @Builder
    public User(Long userId, String nickname, String uuid, Gugun gugun){
        this.userId = userId;
        this.nickname = nickname;
        this.uuid = uuid;
        this.gugun = gugun;
    }

    public void changeProfile(UserProfileRequest userProfileRequest, Gugun gugun){
        this.nickname = userProfileRequest.getNickname();
        this.gugun = gugun;
    }


}