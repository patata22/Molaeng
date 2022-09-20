package idle.molaeng_back.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileRequest {
    private long userId;
    private String nickname;
    private String gugunName;

    @Builder
    public UserProfileRequest(long userId, String nickname, String gugunName){
        this.userId = userId;
        this.nickname = nickname;
        this.gugunName = gugunName;
    }
//    @Builder
//    public UserProfileRequest(User user){
//        this.userId = user.getUserId();
//        this.nickname = user.getNickname();
//        this.gugunName = user.getGugun().getGugunName();
//    }
}
