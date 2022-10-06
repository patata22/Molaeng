package idle.molaeng_back.user.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDTO {
    long userId;
    String nickname;

    boolean isMember;

    @Builder
    public LoginResDTO(long userId, String nickname, boolean isMember) {
        this.userId = userId;
        this.nickname = nickname;
        this.isMember = isMember;
    }
}
