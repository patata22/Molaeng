package idle.molaeng_back.user.model.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileRequest {
    private long userId;
    private String nickname;
    private long gugunId;

    @Builder
    public UserProfileRequest(long userId, String nickname, long gugunId){
        this.userId = userId;
        this.nickname = nickname;
        this.gugunId = gugunId;
    }
//    @Builder
//    public UserProfileRequest(User user){
//        this.userId = user.getUserId();
//        this.nickname = user.getNickname();
//        this.gugunName = user.getGugun().getGugunName();
//    }
}
