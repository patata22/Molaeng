package idle.molaeng_back.auth.model;

import lombok.*;

public class TokenDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Authorization {
        private String accessToken;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Refresh {
        private String accessToken;
        private String refreshToken;
    }
}