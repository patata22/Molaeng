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
    private long myRegionId;

    @Builder
    public UserProfileResponse (long userId, String nickname, long myRegionId){
        this.userId = userId;
        this.nickname = nickname;
        this.myRegionId = myRegionId;
    }

}
