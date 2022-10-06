package idle.molaeng_back.user.model.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResponse {
    private long userId;
    private String nickname;
    private long gugunId;

    @Builder
    public UserProfileResponse (long userId, String nickname, long gugunId){
        this.userId = userId;
        this.nickname = nickname;
        this.gugunId = gugunId;
    }

}
