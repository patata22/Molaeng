package idle.molaeng_back.user.model.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResponse {
    private String nickname;
    private String myRegion;

    @Builder
    public UserProfileResponse (String nickname, String myRegion){
        this.nickname = nickname;
        this.myRegion = myRegion;
    }

}
