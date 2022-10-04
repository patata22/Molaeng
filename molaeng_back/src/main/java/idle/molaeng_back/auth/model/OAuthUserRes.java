package idle.molaeng_back.auth.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthUserRes {

    private String nickname;
    private String profileImg;

    private long uuid;

    @Builder
    public OAuthUserRes(String nickname, String profileImg, long uuid){
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.uuid = uuid;
    }


}
