package idle.molaeng_back.user.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginReqDTO {
    long uuid;
    String nickname;

    public LoginReqDTO(long uuid, String nickname) {
        this.uuid = uuid;
        this.nickname = nickname;
    }
}
