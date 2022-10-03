package idle.molaeng_back.auth.model;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtTokenDto {


    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtTokenDto(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


}